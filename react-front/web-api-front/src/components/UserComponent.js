import React from 'react';
import '../App.css';
import UserService from '../services/UserService';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes, faEdit, faPlus } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

class UserComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            users: []
        }
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
            <div className='list-container '>
                <h1 className = "text-center">Users List</h1>
                <div className="add-button-container">
                    <Link to="/api/users/add" className="add-button">
                        <FontAwesomeIcon icon={faPlus} />    Add User
                    </Link>
                </div>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            {/* <td> User Id</td> */}
                            <td>User Name</td>
                            <td>User Surname</td>
                            <td>User Email</td>
                            <td>User Birthday</td>
                            <td>User Roles</td>
                            <td>Actions</td>
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
                                        <Link to={`/update/${user.id}`} className="update-button">
                                            <FontAwesomeIcon icon={faEdit} />
                                        </Link>
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