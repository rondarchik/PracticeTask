import React from 'react';
import CreditRequestService from '../services/CreditRequestService';

class CreditRequestComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { requests:[] }
    }

    componentDidMount(){
        CreditRequestService.getCreditRequests().then((response) => {
            this.setState({ requests: response.data})
        });
    }

    formatDate(dateString) {
        const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    }

    render (){
        const { requests } = this.state;

        return (
            <div>
                <h1 className = "text-center">Credit Requests List</h1>
                <table className = "table table-striped">
                    <thead>
                    <tr>
                        <td>Request Manager</td>
                        <td>Request Credit</td>
                        <td>Request Date</td>
                        <td>Request Status</td>
                        <td>Request Message</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        requests.map(
                            request =>
                                <tr key={request.id}>
                                    <td>{request.manager}</td>
                                    <td>{request.credit}</td>
                                    <td>{this.formatDate(request.dateOfRequest)}</td>
                                    <td>{request.status}</td>
                                    <td>{request.rejectionMessage}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
        );
    }
}

export default CreditRequestComponent;