import React from 'react';

const axios = require('axios');

export class ResolverReimbursementTableCell extends React.Component {
    constructor(props) {
        super(props);
        this.resolver = this.resolver.bind(this);
    }
    
    resolver(e, res) {
        e.preventDefault();
        axios({
            method: 'post',
            url: `http://localhost:8080/Project1/reimbursements/status/${this.props.reimbursement.id}`,
            data: {
                id: this.props.reimbursement.id,
                status: res,
                userId: window.sessionStorage.getItem("id")
            }
        })
        .then(function (response) {
            console.log(response);
            if (response.status === 201) {
                window.location.href = "/reimbursements";
            } else {
                window.location.href = "/error";
            }
        });
    }

    render() {
        return (
            <tr>
                <td>
                    {this.props.reimbursement.status === "PENDING"
                    ?  <div className='form-group'>
                            <div className="dropdown">
                                <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Reimbursement Status
                                </button>
                                <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <button className="dropdown-item" onClick={(e) => this.resolver(e, "APPROVED")}>Approved</button>
                                    <button className="dropdown-item" onClick={(e) => this.resolver(e, "DENIED")}>Denied</button>
                                </div>
                            </div>
                        </div>
                    : false
                    }
                </td>
                <td>
                    {this.props.reimbursement.id}
                </td>
                <td>
                    {this.props.reimbursement.amount}
                </td>
                <td>
                    {this.props.reimbursement.submitted}
                </td>
                <td>
                    {this.props.reimbursement.resolved}
                </td>
                <td>
                    {this.props.reimbursement.description}
                </td>
                <td>
                    {`${this.props.reimbursement.author.firstName} ${this.props.reimbursement.author.lastName}`}
                </td>
                <td>
                    {this.props.reimbursement.resolver !== null ? `${this.props.reimbursement.resolver.firstName} ${this.props.reimbursement.resolver.lastName}`: `NULL`}
                </td>
                <td>
                    {this.props.reimbursement.status}
                </td>
                <td>
                    {this.props.reimbursement.type}
                </td>
            </tr>
        )
    }
}