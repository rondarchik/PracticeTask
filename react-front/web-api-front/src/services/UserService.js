import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8080/api/users';

class UserService {
    
    getUsersWithRoles() {
        return axios.get(USERS_REST_API_URL);
    }
    
    removeUserById(id) {
        return axios.delete(USERS_REST_API_URL + '/delete/' + id);
    }
}

export default new UserService();
