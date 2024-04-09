import React from 'react';
import CreditService from '../services/CreditService';

class CreditComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { credits:[] }
    }

    componentDidMount(){
        CreditService.getCredits().then((response) => {
            this.setState({ credits: response.data})
        });
    }

    formatDate(dateString) {
        const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    }

    render (){
        const { credits } = this.state;

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
                    {
                        credits.map(
                            credit =>
                                <tr key={credit.id}>
                                    <td>{credit.client}</td>
                                    <td>{credit.creditType}</td>
                                    <td>{credit.paidAmount}</td>
                                    <td>{this.formatDate(credit.startDate)}</td>
                                    <td>{this.formatDate(credit.endDate)}</td>
                                    <td>{credit.status ? "Active" : "Inactive"}</td>
                                </tr>
                        )
                    }

                    </tbody>
                </table>
            </div>
        );
    }
}

export default CreditComponent;