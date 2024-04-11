import axios from 'axios'

const TYPES_REST_API_URL = 'http://localhost:8080/api/credit_types';

export async function getCreditTypes() {
    const response = await axios.get(TYPES_REST_API_URL);
    return response.data;
}
