import React, {useEffect, useState} from 'react';
import {getRequestStatuses} from '../services/RequestStatusService';

export default function ListRequestStatuses() {
    const [statuses, setStatuses] = useState([]);

    useEffect(() => {
        loadStatusesList();
    }, []);

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
                    {statuses.length > 0 ? (
                        statuses.map(
                            status =>
                                <tr key = {status.id}>
                                    <td>{status.id}</td>
                                    <td>{status.status}</td>
                                </tr>
                        ) ) : (
                            <tr>
                                <td colSpan="2">No request statuses found.</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );

    async function loadStatusesList() {
        const statuses = await getRequestStatuses();
        setStatuses(statuses);
    }
}
