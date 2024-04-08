import axios from 'axios'

const ROLES_REST_API_URL = 'http://localhost:8080/api/roles';

class RoleService {
    getRoles() {
        return axios.get(ROLES_REST_API_URL);
    }
}

// eslint-disable-next-line import/no-anonymous-default-export
export default new RoleService();
