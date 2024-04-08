import React from 'react';
import CreditTypeService from '../services/CreditTypeService';

class CreditTypeComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { types:[] }
    }

    componentDidMount(){
        CreditTypeService.getCreditTypes().then((response) => {
            this.setState({ types: response.data})
        });
    }

    render (){
        return (
            <div>
                <h1 className = "text-center">Credit Types List</h1>
                <table className = "table table-striped">
                    <thead>
                    <tr>
                        <td>Credit Name</td>
                        <td>Credit Interest Rate</td>
                        <td>Credit Amount</td>
                        <td>Credit Term (in months)</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.types.map(
                            type =>
                                <tr key={type.id}>
                                    <td>{type.name}</td>
                                    <td>{type.interestRate}</td>
                                    <td>{type.creditAmount}</td>
                                    <td>{type.termInMonths}</td>
                                </tr>
                        )
                    }

                    </tbody>
                </table>
            </div>
        );
    }
}

export default CreditTypeComponent;