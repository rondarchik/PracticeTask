import React, {useEffect, useState} from 'react';
import {getRoles} from '../../services/RoleService';

export default function Roles() {
    const [roles, setRoles] = useState([]);

    useEffect(() => {
        loadRolesList();
    }, []);

    return (
        <div>
            <h1 className = "text-center">Roles List</h1>
            <table className = "table table-striped">
                <thead>
                <tr>
                    <td>Role Id</td>
                    <td>Role Name</td>
                </tr>
                </thead>
                <tbody>
                    {roles.length > 0 ? (
                        roles.map(
                            role =>
                                <tr key = {role.id}>
                                    <td>{role.id}</td>
                                    <td>{role.roleName}</td>
                                </tr>
                        ) ) : (
                            <tr>
                                <td colSpan="2">No roles found.</td>
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
}
