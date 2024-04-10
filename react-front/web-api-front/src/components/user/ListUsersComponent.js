import '../../App.css';
import React, { useEffect, useState } from 'react'
import { getUsers, removeUserById } from '../../services/UserService';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes, faPlus, faEdit } from '@fortawesome/free-solid-svg-icons';
import { Link } from "react-router-dom";


export default function ListUsers() {
    const [users, setUsers] = useState([]);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadUsersList();
    }, [reload]);

    return (
        <div className='list-container '>
            <h1 className="text-center">Users List</h1>
            <div className="add-button-container">
                <Link className="add-button" to="add">
                    <FontAwesomeIcon icon={faPlus}/>   Add User
                </Link>
            </div>
            <table className="table table-striped">
                <thead>
                <tr>
                    <td>User Name</td>
                    <td>User Surname</td>
                    <td>User Email</td>
                    <td>User Birthday</td>
                    <td>Roles</td>
                    <td>Actions</td>
                </tr>
                </thead>
                <tbody>
                    {users.length > 0 ? (
                        users.map(
                            user =>
                                <tr key={user.id}>
                                    <td>{user.name}</td>
                                    <td>{user.surname}</td>
                                    <td>{user.email}</td>
                                    <td>{formatDate(user.birthDate)}</td>
                                    <td>{user.roles.map(role => role.roleName).join('\n')}</td>
                                    <td>
                                        <button className="delete-button" onClick={() => deleteUser(user.id)}>
                                            <FontAwesomeIcon icon={faTimes}/>
                                        </button>
                                        <Link className="update-button left-15" to={`update/${user.id}`}>
                                            <FontAwesomeIcon icon={faEdit}/>
                                        </Link>
                                    </td>
                                </tr>
                        ) ) : (
                            <tr>
                                <td colSpan="6">No users found.</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );

    async function loadUsersList() {
        const users = await getUsers();
        setUsers(users);
    }

    async function deleteUser(userId) {
        await removeUserById(userId);
        setReload(!reload);
    }

    function formatDate(dateString) {
        const options = {day: '2-digit', month: '2-digit', year: 'numeric'};
        return new Date(dateString).toLocaleDateString(undefined, options);
    }
}
