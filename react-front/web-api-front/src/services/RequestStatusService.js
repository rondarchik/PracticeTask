import axios from 'axios'

const STATUSES_REST_API_URL = 'http://localhost:8080/api/request_statuses';

export async function getRequestStatuses() {
    const response = await axios.get(STATUSES_REST_API_URL);
    return response.data;
}
