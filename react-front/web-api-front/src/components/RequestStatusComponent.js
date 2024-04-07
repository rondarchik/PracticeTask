import React from 'react';
import RequestStatusService from '../services/RequestStatusService';

class RequestStatusComponent extends React.Component {
    constructor(props) {
        super(props); 
        this.state = { statuses:[] }
    }

    componentDidMount(){
        RequestStatusService.getRequestStatuses().then((response) => {
            this.setState({ statuses: response.data})
        });
    }

    render (){
        return (
            <div>
                <h1 className = "text-center">Request Statuses List</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td>Status Id</td>
                            <td>Status Name</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.statuses.map(
                                status => 
                                <tr key = {status.id}>
                                    <td>{status.id}</td>   
                                    <td>{status.status}</td>   
                                </tr>
                            )
                        }

                    </tbody>
                </table>
            </div>
        );
    }
}

export default RequestStatusComponent;