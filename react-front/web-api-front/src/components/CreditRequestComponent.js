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

    render (){
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
                        this.state.requests.map(
                            request =>
                                <tr key={request.id}>
                                    <td>{request.idManager}</td>
                                    <td>{request.idCredit}</td>
                                    <td>{request.dateOfRequest}</td>
                                    <td>{request.idStatus}</td>
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