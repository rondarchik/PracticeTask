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

    render (){
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
                        this.state.credits.map(
                            credit =>
                                <tr key={credit.id}>
                                    <td>{credit.idClient}</td>
                                    <td>{credit.idCreditType}</td>
                                    <td>{credit.paidAmount}</td>
                                    <td>{credit.startDate}</td>
                                    <td>{credit.endDate}</td>
                                    <td>{credit.status}</td>
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