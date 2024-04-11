import axios from 'axios'

const REQUESTS_REST_API_URL = 'http://localhost:8080/api/credit_requests';

export async function getCreditRequests() {
    const response = await axios.get(REQUESTS_REST_API_URL);
    return response.data;
}
