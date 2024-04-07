import axios from 'axios'

const CREDITS_REST_API_URL = 'http://localhost:8080/api/credits';

class CreditService {
    getCredits() {
        return axios.get(CREDITS_REST_API_URL);
    }
}

export default new CreditService();
