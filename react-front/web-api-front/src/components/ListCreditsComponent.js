import React, {useEffect, useState} from 'react';
import {getCredits} from '../services/CreditService';
import formatDate from "./FormatDateComponent";

export default function ListCredits() {
    const [credits, setCredits] = useState([]);

    useEffect(() => {
        loadCreditsList();
    }, []);

    return (
        <div>
            <h1 className = "text-center">Credits List</h1>
            <table className = "table table-striped">
                <thead>
                <tr>
                    <td>Credit Client</td>
                    <td>Credit Type</td>
                    <td>Credit Paid Amount</td>
                    <td>Credit Start Date</td>
                    <td>Credit End Date</td>
                    <td>Credit Status</td>
                </tr>
                </thead>
                <tbody>
                    {credits.length > 0 ? (
                        credits.map(
                            credit =>
                                <tr key={credit.id}>
                                    <td>{credit.client}</td>
                                    <td>{credit.creditType.name} ({credit.creditType.termInMonths} months)</td>
                                    <td>{credit.paidAmount}</td>
                                    <td>{formatDate(credit.startDate)}</td>
                                    <td>{formatDate(credit.endDate)}</td>
                                    <td>{credit.status ? "Active" : "Inactive"}</td>
                                </tr>
                        ) ) : (
                            <tr>
                                <td colSpan="6">No credits found.</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );

    async function loadCreditsList() {
        const credits = await getCredits();
        setCredits(credits);
    }
}
