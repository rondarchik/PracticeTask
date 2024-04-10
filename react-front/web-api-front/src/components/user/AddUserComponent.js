import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { getRoles } from '../../services/RoleService';
import Select from 'react-select';
import { addUser } from "../../services/UserService";
import '../../App.css';

export default function AddUser() {
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [email, setEmail] = useState('');
    const [birthDate, setBirthDate] = useState('');
    const [passwordHash, setPasswordHash] = useState('');
    const [roles, setRoles] = useState([]);

    let rolesList = [];
    loadRoles();

    return (
        <section className="form-container">
            <h2>Add New User</h2>
            <div className="form">
                <div className="form-group">
                    <label htmlFor="name">Name:</label>
                    <input className="form-control" type="text" id="name" name="name"
                           onChange={(e) => setName(e.target.value)}
                           placeholder="Enter name"/>
                </div>
                <div className="form-group">
                    <label htmlFor="surname">Surname:</label>
                    <input className="form-control" type="text" id="surname" name="surname"
                           onChange={(e) => setSurname(e.target.value)}
                           placeholder="Enter surname"/>
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input className="form-control" type="email" id="email" name="email"
                           aria-describedby="emailHelp"
                           onChange={(e) => setEmail(e.target.value)}
                           placeholder="Enter email"/>
                </div>
                <div className="form-group">
                    <label htmlFor="passwordHash">Password:</label>
                    <input className="form-control" type="password" id="passwordHash" name="passwordHash"
                           onChange={(e) => setPasswordHash(e.target.value)}
                           placeholder="Enter password"/>
                </div>
                <div className="form-group">
                    <label htmlFor="birthDate">Birth Date:</label>
                    <input className="form-control" type="date" id="birthDate" name="birthDate"
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
                        value={roles}
                        options={rolesList}
                        onChange={handleSelectRole}
                        isSearchable={true}
                        isMulti
                    />
                </div>
                <div className="form-btn-group">
                    <button type="submit" className="btn btn-primary" onClick={addNewUser}>Save</button>
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

    async function addNewUser() {
        let user = {
            name : name,
            surname : surname,
            email : email,
            birthDate : birthDate,
            passwordHash : passwordHash,
            roles : roles.map(role => role.value)
        };
        console.log(user);

        await addUser(user);
        navigate('/api/users');
    }
}
