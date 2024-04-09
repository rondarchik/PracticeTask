import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8080/api/users';

class UserService {

    getUsersWithRoles() {
        return axios.get(USERS_REST_API_URL);
    }

    addUser(user) {
        return axios.post(USERS_REST_API_URL + '/add', user);
    }

    removeUserById(id) {
        return axios.delete(`${USERS_REST_API_URL}/delete/${id}`);
    }

    updateUser(id, updatedUser) {
        return axios.put(`${USERS_REST_API_URL}/update/${id}`, updatedUser);
    }

    getUserById(id) {
        return axios.get(`${USERS_REST_API_URL}/${id}`);
    }
}

// eslint-disable-next-line import/no-anonymous-default-export
export default new UserService();
