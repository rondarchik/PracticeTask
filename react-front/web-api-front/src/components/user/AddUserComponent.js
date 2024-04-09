import React from 'react';
import { Link } from 'react-router-dom';
import UserService from '../../services/UserService'
import RoleService from '../../services/RoleService';
import Select from 'react-select';

class AddUserComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            surname: '',
            email: '',
            passwordHash: '',
            birthDate: '',
            roles: [],
            selectedRoles: []
        };
    }

    componentDidMount() {
        RoleService.getRoles()
            .then(response => {
                this.setState({ roles: response.data });
            })
            .catch(error => {
                console.error('Error retrieving roles:', error);
            });
    }

    handleChange = (event) => {
        if (event.target.name === 'role') {
            const selectedOptions = Array.from(event.target.selectedOptions, option => option.value);
            this.setState({ selectedRoles: selectedOptions });
        } else {
            this.setState({ [event.target.name]: event.target.value });
        }
    };

    handleSubmit = event => {
        event.preventDefault();
        const { name, surname, email, passwordHash, birthDate, selectedRoles } = this.state;
        const newUser = { name, surname, email, passwordHash, birthDate, roles: selectedRoles };
        console.log(newUser, selectedRoles);
        UserService.addUser(newUser)
            .then(response => {
                console.log('User added successfully:', response.data);
                this.props.history.push('http://localhost:8080/api/users');
            })
            .catch(error => {
                console.error('Error adding user:', error);
            });
    }

    render() {
        return (
            <div>
                <h2>Add New User</h2>
                <form onSubmit={this.handleSubmit} className="form-container">
                    <div className="form-group">
                        <label htmlFor="name">Name:</label>
                        <input className="form-control" type="text" id="name" name="name"
                               value={this.state.name} onChange={this.handleChange} placeholder="Enter name" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="surname">Surname:</label>
                        <input className="form-control" type="text" id="surname" name="surname"
                               value={this.state.surname} onChange={this.handleChange} placeholder="Enter surname" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email:</label>
                        <input className="form-control" type="email" id="email" name="email"
                               value={this.state.email} aria-describedby="emailHelp" onChange={this.handleChange} placeholder="Enter email" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="passwordHash">Password:</label>
                        <input className="form-control" type="password" id="passwordHash" name="passwordHash"
                               value={this.state.passwordHash} onChange={this.handleChange} placeholder="Enter password" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="birthDate">Birth Date:</label>
                        <input className="form-control" type="date" id="birthDate" name="birthDate"
                               value={this.state.birthDate} onChange={this.handleChange} placeholder="Enter birth date" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="role">Role:</label>
                        <Select
                            className="form-control"
                            id="role"
                            name="role"
                            options={this.state.roles.map(role => ({ key: role.id, value: role.roleName, label: role.roleName }))}
                            isMulti
                            onChange={(selectedOptions) => {
                                const selectedRoles = selectedOptions.map(option => this.state.roles.find(role => role.roleName === option.value));
                                this.setState({
                                    selectedRoles: selectedRoles,
                                });
                            }}
                        />
                    </div>
                    <div className="form-btn-group">
                        <button type="submit" className="btn btn-primary">Save</button>
                        <Link to="/api/users">Back to Users</Link>
                    </div>
                </form>
            </div>
        );
    }
}

export default AddUserComponent;
