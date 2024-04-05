import React from 'react';
import UserService from '../services/UserService';
// import RoleService from '../services/RoleService';

class UserComponent extends React.Component {
    constructor(props) {
        super(props); 
        this.state = { users:[], userRoles:[] }
    }

    componentDidMount(){
        UserService.getUsers().then((response) => {
            this.setState({ users: response.data})
        });

        // UserService.getUserRoles().then((response) => {
        //     this.setState({ userRoles: response.data})
        // });
    }

    formatDate(dateString) {
        const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    }

    render (){
        return (
            <div>
                <h1 className = "text-center">Users List</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            {/* <td> User Id</td> */}
                            <td>User Name</td>
                            <td>User Surname</td>
                            <td>User Email</td>
                            <td>User Birthday</td>
                            {/* <td>User Roles</td> */}
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user => 
                                <tr key = {user.id}>
                                    {/* <td> {user.id}</td>    */}
                                    <td>{user.name}</td>   
                                    <td>{user.surname}</td>   
                                    <td>{user.email}</td>     
                                    <td>{this.formatDate(user.birthDate)}</td> 
                                    {/* <td>{this.state.userRoles[user.id]?.join(', ')}</td> */}
                                </tr>
                            )
                        }

                    </tbody>
                </table>
            </div>
        );
    }
}

export default UserComponent;