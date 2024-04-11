import React, { useEffect, useState } from 'react';
import {getRoles, removeRoleById, removeUserFromRole} from '../../services/RoleService';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTimes, faPlus } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";

export default function ListRoles() {
    const [roles, setRoles] = useState([]);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadRolesList();
    }, [reload]);

    return (
        <div>
            <h1 className="text-center">Roles List</h1>
            <div className="add-button-container">
                <Link className="add-button" to="add">
                    <FontAwesomeIcon icon={faPlus}/> Add Role
                </Link>
            </div>
            <table className="table table-striped">
                <thead>
                <tr>
                    <td>Role Id</td>
                    <td>Role Name</td>
                    <td>Users</td>
                    <td>Actions</td>
                </tr>
                </thead>
                <tbody>
                {roles.length > 0 ? (
                    roles.map(
                        role =>
                            <tr key={role.id}>
                                <td>{role.id}</td>
                                <td>{role.roleName}</td>
                                <td>
                                    {role.users.map(user => (
                                        <div key={user.id}>
                                            {user.email}
                                            <button className="delete-button left-15"
                                                    onClick={() => deleteUserFromRole(role.id, user.id)}>
                                                <FontAwesomeIcon icon={faTimes}/>
                                            </button>
                                        </div>
                                    ))}
                                </td>
                                <td>
                                    <button className="delete-button" onClick={() => deleteRole(role.id)}>
                                        <FontAwesomeIcon icon={faTimes}/>
                                    </button>
                                    <Link className="update-button left-15" to={`update/${role.id}`}>
                                        <FontAwesomeIcon icon={faEdit}/>
                                    </Link>
                                </td>
                            </tr>
                    )) : (
                    <tr>
                        <td colSpan="3">No roles found.</td>
                    </tr>
                )
                }
                </tbody>
            </table>
        </div>
    );

    async function loadRolesList() {
        const roles = await getRoles();
        setRoles(roles);
    }

    async function deleteRole(roleId) {
        await removeRoleById(roleId);
        setReload(!reload);
    }

    async function deleteUserFromRole(roleId, userId) {
        await removeUserFromRole(roleId, userId);
        setReload(!reload);
    }
}
