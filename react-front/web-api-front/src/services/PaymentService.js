import axios from 'axios'

const PAYMENTS_REST_API_URL = 'http://localhost:8080/api/payments';

class PaymentService {
    getPayments() {
        return axios.get(PAYMENTS_REST_API_URL);
    }
}

export default new PaymentService();
