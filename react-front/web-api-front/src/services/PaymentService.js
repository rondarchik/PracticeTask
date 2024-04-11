import axios from 'axios'

const PAYMENTS_REST_API_URL = 'http://localhost:8080/api/payments';

export async function getPayments() {
    const response = await axios.get(PAYMENTS_REST_API_URL);
    return response.data;
}
