import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8080/api/users';
const ROLES_REST_API_URL = 'http://localhost:8080/api/roles';

class UserService {
    getUsers() {
        return axios.get(USERS_REST_API_URL);
    }
    
    getUserRoles(userId) {
        return axios.get(`${ROLES_REST_API_URL}/${userId}`);
    }
}

export default new UserService();
