import React, { useState } from 'react';
import { Link, useLoaderData, useNavigate } from 'react-router-dom';
import { getRoles } from '../../services/RoleService';
import Select from 'react-select';
import {getUserById, updateUser} from "../../services/UserService";
import '../../App.css';

export async function loader({ params }) {
    const user = await getUserById(params.id);
    return { user };
}

export default function UpdateUser() {
    const navigate = useNavigate();

    const { user } = useLoaderData();

    const [name, setName] = useState(user.name);
    const [surname, setSurname] = useState(user.surname);
    const [email, setEmail] = useState(user.email);
    const [birthDate, setBirthDate] = useState(user.birthDate);
    const [passwordHash, setPasswordHash] = useState(user.passwordHash);

    console.log(user);

    const [roles, setRoles] = useState(user.roles ?
        user.roles.map(role => ({value: role, label: `${role.roleName}`})) : []);



    let rolesList = [];
    loadRoles();

    return (
        <section className="form-container">
            <h2>Update User</h2>
            <div className="form">
                <div className="form-group">
                    <label htmlFor="name">Name:</label>
                    <input className="form-control" type="text" id="name" name="name"
                           value={name}
                           onChange={(e) => setName(e.target.value)}
                           placeholder="Enter name"/>
                </div>
                <div className="form-group">
                    <label htmlFor="surname">Surname:</label>
                    <input className="form-control" type="text" id="surname" name="surname"
                           value={surname}
                           onChange={(e) => setSurname(e.target.value)}
                           placeholder="Enter surname"/>
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input className="form-control" type="email" id="email" name="email"
                           aria-describedby="emailHelp"
                           value={email}
                           onChange={(e) => setEmail(e.target.value)}
                           placeholder="Enter email"/>
                </div>
                <div className="form-group">
                    <label htmlFor="passwordHash">Password:</label>
                    <input className="form-control" type="password" id="passwordHash" name="passwordHash"
                           value={passwordHash}
                           onChange={(e) => setPasswordHash(e.target.value)}
                           placeholder="Enter password"/>
                </div>
                <div className="form-group">
                    <label htmlFor="birthDate">Birth Date:</label>
                    <input className="form-control" type="date" id="birthDate" name="birthDate"
                           value={formatDate(birthDate)}
                           onChange={(e) => setBirthDate(e.target.value)}
                           placeholder="Enter birth date"/>
                </div>
                <div className="form-group">
                    <label htmlFor="role">Role:</label>
                    <Select
                        className="form-control"
                        placeholder="Roles"
                        id="role"
                        name="role"
                        defaultValue={roles}
                        options={rolesList}
                        onChange={handleSelectRole}
                        isSearchable={true}
                        isMulti
                    />
                </div>
                <div className="form-btn-group">
                    <button type="submit" className="btn btn-primary" onClick={updateUserClick}>Save</button>
                    <Link to="/api/users">Back to Users</Link>
                </div>
            </div>
        </section>
    );

    async function loadRoles() {
        const roles = await getRoles();
        roles.map(role =>
            rolesList.push({
                key: role.id,
                value: role,
                label: role.roleName
            })
        );
    }

    function handleSelectRole(data) {
        setRoles(data);
    }

    async function updateUserClick() {
        user.name = name;
        user.surname = surname;
        user.email = email;
        user.birthDate = birthDate;
        user.passwordHash = passwordHash;
        user.roles = roles.map(role => role.value)

        console.log(user);

        await updateUser(user, user.id);
        navigate('/api/users');
    }

    function formatDate(dateString) {
        return new Date(dateString).toISOString().split('T')[0];
    }
}
