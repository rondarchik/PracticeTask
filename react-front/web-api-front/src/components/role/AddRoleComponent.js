import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { addRole } from '../../services/RoleService';
import '../../App.css';

export default function AddRole() {
    const navigate = useNavigate();

    const [roleName, setRoleName] = useState('');

    return (
        <section className="form-container">
            <h2>Add New Role</h2>
            <div className="form">
                <div className="form-group">
                    <label htmlFor="roleName">Role Name:</label>
                    <input className="form-control" type="text" id="roleName" name="roleName"
                           onChange={(e) => setRoleName(e.target.value)}
                           placeholder="Enter role name"/>
                </div>
                <div className="form-btn-group">
                    <button type="submit" className="btn btn-primary" onClick={addNewRole}>Save</button>
                    <Link to="/api/roles">Back to Roles</Link>
                </div>
            </div>
        </section>
    );

    async function addNewRole() {
        let role = {};
        role.id = crypto.randomUUID();
        role.roleName = roleName;
        console.log(role);

        await addRole(role);
        navigate('/api/roles');
    }
}
