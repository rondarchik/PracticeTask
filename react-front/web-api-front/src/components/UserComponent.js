import React from 'react';
import UserService from '../services/UserService';
import '../App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

class UserComponent extends React.Component {
    constructor(props) {
        super(props); 
        this.state = { users:[] }
    }

    componentDidMount(){
        UserService.getUsersWithRoles().then((response) => {
            this.setState({ users: response.data})
        });
    }

    formatDate(dateString) {
        const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    }

    handleDelete(id) {
        UserService.removeUserById(id)
            .then(() => {
                this.setState(prevState => ({
                    users: prevState.users.filter(user => user.id !== id)
                }));
            });
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
                            <td>User Roles</td>
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
                                    <td>{user.roles.join('\n')}</td>
                                    <td>
                                        <button className="delete-button" onClick={() => this.handleDelete(user.id)}>
                                            <FontAwesomeIcon icon={faTimes} />
                                        </button>
                                    </td>
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