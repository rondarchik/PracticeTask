import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8080/api/users';

export async function getUsers() {
    const response = await axios.get(USERS_REST_API_URL);
    return response.data;
}

export async function addUser(user) {
    const response = await axios.post(USERS_REST_API_URL + '/add', user);
    return response.data;
}

export async function removeUserById(id) {
    const response = await axios.delete(`${USERS_REST_API_URL}/delete/${id}`);
    return response.data;
}

export async function updateUser(user, id) {
    const response = await axios.put(`${USERS_REST_API_URL}/update/${id}`, user);
    return response.data;
}

export async function getUserById(id) {
    const response = await axios.get(`${USERS_REST_API_URL}/${id}`);
    return response.data;
}
