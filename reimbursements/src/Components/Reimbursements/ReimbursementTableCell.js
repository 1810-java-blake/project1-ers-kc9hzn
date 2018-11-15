import React from 'react';

export class ReimbursementTableCell extends React.Component {
    render() {
        return (
            <tr>
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