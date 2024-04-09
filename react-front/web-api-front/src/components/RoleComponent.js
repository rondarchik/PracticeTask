import React from 'react';
import RoleService from '../services/RoleService';

class RoleComponent extends React.Component {
    constructor(props) {
        super(props); 
        this.state = { roles:[] }
    }

    componentDidMount(){
        RoleService.getRoles().then((response) => {
            this.setState({ roles: response.data})
        });
    }

    render (){
        const { roles } = this.state;

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
                        {
                            roles.map(
                                role => 
                                <tr key = {role.id}>
                                    <td>{role.id}</td>   
                                    <td>{role.roleName}</td>   
                                </tr>
                            )
                        }

                    </tbody>
                </table>
            </div>
        );
    }
}

export default RoleComponent;