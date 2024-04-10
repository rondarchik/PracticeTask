import React from 'react';
import PaymentService from "../services/PaymentService";

class PaymentComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { payments:[] }
    }

    componentDidMount(){
        PaymentService.getPayments().then((response) => {
            this.setState({ payments: response.data})
        });
    }

    formatDate(dateString) {
        const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    }

    render (){
        const { payments } = this.state;

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
                                    <td>{this.formatDate(payment.paymentDate)}</td>
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
    }
}

export default PaymentComponent;