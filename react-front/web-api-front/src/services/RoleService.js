import axios from 'axios'

const ROLES_REST_API_URL = 'http://localhost:8080/api/roles';

export async function getRoles() {
    const response = await axios.get(ROLES_REST_API_URL);
    return response.data;
}

export async function addRole(role) {
    const response = await axios.post(`${ROLES_REST_API_URL}/add'`, role);
    return response.data;
}

export async function removeRoleById(id) {
    const response = await axios.delete(`${ROLES_REST_API_URL}/delete/${id}`);
    return response.data;
}

export async function updateRole(role, id) {
    const response = await axios.put(`${ROLES_REST_API_URL}/update/${id}`, role);
    return response.data;
}

export async function getRoleById(id) {
    const response = await axios.get(`${ROLES_REST_API_URL}/${id}`);
    return response.data;
}