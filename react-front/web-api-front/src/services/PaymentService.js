import axios from 'axios'

const PAYMENTS_REST_API_URL = 'http://localhost:8080/api/payments';

class PaymentService {
    getPayments() {
        return axios.get(PAYMENTS_REST_API_URL);
    }
}

// eslint-disable-next-line import/no-anonymous-default-export
export default new PaymentService();
