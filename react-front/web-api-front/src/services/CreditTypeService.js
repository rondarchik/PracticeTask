import axios from 'axios'

const TYPES_REST_API_URL = 'http://localhost:8080/api/credit_types';

class CreditTypeService {
    getCreditTypes() {
        return axios.get(TYPES_REST_API_URL);
    }
}

export default new CreditTypeService();
