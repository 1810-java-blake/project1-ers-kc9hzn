import React from 'react';import {ResolverReimbursementTableCell} from './ResolverReimbursementTableCell.js';
import {ReimbursementTableCell} from './ReimbursementTableCell.js';
import {Link} from 'react-router-dom';
const axios = require('axios');


export class ViewReimbursements extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            reimbursements: [
            ]
        }
    }
    
    componentDidMount() {
        if (window.sessionStorage.getItem("username") === null) {
            window.location.href = "/login";
        } else {
            if (window.sessionStorage.getItem("userRole") !== "USER" && window.sessionStorage.getItem("selectedUserRole") !== "USER") {
                axios({
                    method: 'GET',
                    url: `http://localhost:8080/Project1/reimbursements/`
                })
                .then((response) => {
                    this.setState({
                        reimbursements: response.data
                    })
                    console.log(response.data);
                });
            } else {
                axios({
                    method: 'GET',
                    url: `http://localhost:8080/Project1/reimbursements/user/${window.sessionStorage.getItem("id")}`
                })
                .then((response) => {
                    this.setState({
                        reimbursements: response.data
                    })
                    console.log(response.data);
                });
            }
        }
    }

    render() {
        if (window.sessionStorage.getItem("userRole") === "USER" || window.sessionStorage.getItem("selectedUserRole") === "USER") {
            return (
                <div>
                    <Link to="/add">
                    <button type="button" className="btn btn-outline-primary">Submit New Reimbursement</button>
                    </Link>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>
                                    ID
                                </th>
                                <th>
                                    Amount
                                </th>
                                <th>
                                    Date Submitted
                                </th>
                                <th>
                                    Date Resolved
                                </th>
                                <th>
                                    Description
                                </th>
                                <th>
                                    Author
                                </th>
                                <th>
                                    Resolver
                                </th>
                                <th>
                                    Status
                                </th>
                                <th>
                                    Type
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.reimbursements.map(reimbursement =>
                                    <ReimbursementTableCell reimbursement={reimbursement} key={reimbursement.id} />
                                )
                            }
                        </tbody>
                    </table>
                </div>
            )
        } else {
            return (
                <div>
                    <div>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>
                                    Resolve?
                                </th>
                                <th>
                                    ID
                                </th>
                                <th>
                                    Amount
                                </th>
                                <th>
                                    Date Submitted
                                </th>
                                <th>
                                    Date Resolved
                                </th>
                                <th>
                                    Description
                                </th>
                                <th>
                                    Author
                                </th>
                                <th>
                                    Resolver
                                </th>
                                <th>
                                    Status
                                </th>
                                <th>
                                    Type
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.reimbursements.map(reimbursement =>
                                    <ResolverReimbursementTableCell reimbursement={reimbursement} key={reimbursement.id} />
                                )
                            }
                        </tbody>
                    </table>
                </div>
                </div>
            )
        }
    }
}