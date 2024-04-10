import React, { useState } from 'react';
import { Link, useLoaderData, useNavigate } from 'react-router-dom';
import { getRoleById, updateRole } from '../../services/RoleService';
import '../../App.css';

export async function loader({ params }) {
    const role = await getRoleById(params.id);
    return { role };
}

export default function UpdateRole() {
    const navigate = useNavigate();

    const { role } = useLoaderData();
    console.log(role);

    const [roleName, setRoleName] = useState(role.roleName);

    return (
        <section className="form-container">
            <h2>Update Role</h2>
            <div className="form">
                <div className="form-group">
                    <label htmlFor="name">Role Name:</label>
                    <input className="form-control" type="text" id="name" name="name"
                           value={roleName}
                           onChange={(e) => setRoleName(e.target.value)}
                           placeholder="Enter role name"/>
                </div>
                <div className="form-btn-group">
                    <button type="submit" className="btn btn-primary" onClick={updateRoleClick}>Save</button>
                    <Link to="/api/roles">Back to Roles</Link>
                </div>
            </div>
        </section>
    );

    async function updateRoleClick() {
        role.roleName = roleName;
        console.log(role);

        await updateRole(role, role.id);
        navigate('/api/roles');
    }
}
