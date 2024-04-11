import React, {useEffect, useState} from 'react';
import {getCreditRequests} from '../services/CreditRequestService';

export default function ListCreditRequests() {
    const [requests, setRequests] = useState([]);

    useEffect(() => {
        loadRequestsList();
    }, []);

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
                    {requests.length > 0 ? (
                        requests.map(
                            request =>
                                <tr key={request.id}>
                                    <td>{request.manager}</td>
                                    <td>{request.credit}</td>
                                    <td>{formatDate(request.dateOfRequest)}</td>
                                    <td>{request.status}</td>
                                    <td>{request.rejectionMessage}</td>
                                </tr>
                        ) ) : (
                            <tr>
                                <td colSpan="5">No credit requests found.</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );

    async function loadRequestsList() {
        const requests = await getCreditRequests();
        setRequests(requests);
    }

    function formatDate(dateString) {
        const options = {day: '2-digit', month: '2-digit', year: 'numeric'};
        return new Date(dateString).toLocaleDateString(undefined, options);
    }
}
