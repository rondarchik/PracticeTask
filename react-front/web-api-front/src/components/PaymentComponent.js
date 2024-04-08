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

    render (){
        return (
            <div>
                <h1 className = "text-center">Payments List</h1>
                <table className = "table table-striped">
                    <thead>
                    <tr>
                        <td>Client Id</td>
                        <td>Credit Id</td>
                        <td>Amount</td>
                        <td>Payment Date</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.payments.map(
                            payment =>
                                <tr key={payment.id}>
                                    <td>{payment.idClient}</td>
                                    <td>{payment.idCredit}</td>
                                    <td>{payment.amount}</td>
                                    <td>{payment.paymentDate}</td>
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