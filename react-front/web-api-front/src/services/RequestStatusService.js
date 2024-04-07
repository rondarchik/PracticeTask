import axios from 'axios'

const STATUSES_REST_API_URL = 'http://localhost:8080/api/request_statuses';

class RequestStatusService {
    getRequestStatuses() {
        return axios.get(STATUSES_REST_API_URL);
    }
}

export default new RequestStatusService();
