import axios from 'axios'

const ROLES_REST_API_URL = 'http://localhost:8080/api/roles';

export async function getRoles() {
    const response = await axios.get(ROLES_REST_API_URL);
    return response.data;
}

