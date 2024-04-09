import React, { useState } from 'react';
import Select from 'react-select';
import {Link} from "react-router-dom";

function UserForm() {
    const [user, setUser] = useState({
        name: '',
        surname: '',
        email: '',
        passwordHash: '',
        birthDate: '',
        roles: []
    })

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('<http://localhost:8080/api/users>', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
            .then(response => response.json())
            .then(data => console.log('User created:', data))
            .catch(error => console.error('Error creating user:', error));
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setUser(prevUser => ({ ...prevUser, [name]: value }));
    };

    return (
        <div>
            <h2>Add New User</h2>
            <form onSubmit={handleSubmit} className="form-container">
                <div className="form-group">
                    <label htmlFor="name">Name:</label>
                    <input className="form-control" type="text" id="name" name="name"
                           value={this.state.name} onChange={handleChange} placeholder="Enter name"/>
                </div>
                <div className="form-group">
                    <label htmlFor="surname">Surname:</label>
                    <input className="form-control" type="text" id="surname" name="surname"
                           value={this.state.surname} onChange={handleChange} placeholder="Enter surname"/>
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input className="form-control" type="email" id="email" name="email"
                           value={this.state.email} aria-describedby="emailHelp" onChange={handleChange}
                           placeholder="Enter email"/>
                </div>
                <div className="form-group">
                    <label htmlFor="passwordHash">Password:</label>
                    <input className="form-control" type="password" id="passwordHash" name="passwordHash"
                           value={this.state.passwordHash} onChange={handleChange} placeholder="Enter password"/>
                </div>
                <div className="form-group">
                    <label htmlFor="birthDate">Birth Date:</label>
                    <input className="form-control" type="date" id="birthDate" name="birthDate"
                           value={this.state.birthDate} onChange={handleChange} placeholder="Enter birth date"/>
                </div>
                {/*<div className="form-group">*/}
                {/*    <label htmlFor="role">Role:</label>*/}
                {/*    <Select*/}
                {/*        className="form-control"*/}
                {/*        id="role"*/}
                {/*        name="role"*/}
                {/*        options={this.state.roles.map(role => ({value: role.roleName, label: role.roleName}))}*/}
                {/*        isMulti*/}
                {/*        onChange={(selectedOptions) => {*/}
                {/*            const selectedRoles = selectedOptions.map(option => option.value);*/}
                {/*            this.setState({selectedRoles});*/}
                {/*        }}*/}
                {/*    />*/}
                {/*    /!* <select className="form-control" id="role" name="role" multiple>*/}
                {/*            {this.state.roles.map(role => (*/}
                {/*                <option key={role.id} value={role.roleName}>{role.roleName}</option>*/}
                {/*            ))}*/}
                {/*        </select> *!/*/}
                {/*</div>*/}
                <div className="form-btn-group">
                    <button type="submit" className="btn btn-primary">Save</button>
                    <Link to="/api/users">Back to Users</Link>
                </div>
            </form>
        </div>
    );
}

export default UserForm;