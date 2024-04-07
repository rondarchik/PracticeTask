import axios from 'axios'

const REQUESTS_REST_API_URL = 'http://localhost:8080/api/credit_requests';

class CreditRequestService {
    getCreditRequests() {
        return axios.get(REQUESTS_REST_API_URL);
    }
}

export default new CreditRequestService();
