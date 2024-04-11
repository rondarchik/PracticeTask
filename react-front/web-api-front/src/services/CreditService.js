import axios from 'axios'

const CREDITS_REST_API_URL = `http://localhost:8080/api/credits`;

export async function getCredits() {
    const response = await axios.get(CREDITS_REST_API_URL);
    return response.data;
}
