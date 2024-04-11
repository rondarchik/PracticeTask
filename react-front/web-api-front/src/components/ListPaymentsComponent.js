import React, {useEffect, useState} from 'react';
import {getPayments} from "../services/PaymentService";

export default function ListPayments() {
    const [payments, setPayments] = useState([]);

    useEffect(() => {
        loadPaymentsList();
    }, []);


    return (
        <div>
            <h1 className = "text-center">Payments List</h1>
            <table className = "table table-striped">
                <thead>
                <tr>
                    <td>Client</td>
                    <td>Credit Id</td>
                    <td>Amount</td>
                    <td>Payment Date</td>
                </tr>
                </thead>
                <tbody>
                    {payments.length > 0 ? (
                        payments.map(
                            payment =>
                                <tr key={payment.id}>
                                    <td>{payment.client}</td>
                                    <td>{payment.credit}</td>
                                    <td>{payment.amount}</td>
                                    <td>{formatDate(payment.paymentDate)}</td>
                                </tr>
                        ) ) : (
                            <tr>
                                <td colSpan="5">No payments found.</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );

    async function loadPaymentsList() {
        const payments = await getPayments();
        setPayments(payments);
    }

    function formatDate(dateString) {
        const options = {day: '2-digit', month: '2-digit', year: 'numeric'};
        return new Date(dateString).toLocaleDateString(undefined, options);
    }
}

