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
        this.resolver = this.resolver.bind(this);
    }

    resolver(e, res) {
        e.preventDefault();
        switch(res) {
            case "APPROVED":
                window.location.href = "/approved"
                break;
            case "DENIED":
                    window.location.href = "/denied"
                break;
            case "PENDING":
                    window.location.href = "/pending"
                break;
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
                <>
                <div className='form-group'>
                            <div className="dropdown">
                                <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Filter By Status
                                </button>
                                <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <button className="dropdown-item" onClick={(e) => this.resolver(e, "APPROVED")}>Approved</button>
                                    <button className="dropdown-item" onClick={(e) => this.resolver(e, "DENIED")}>Denied</button>
                                    <button className="dropdown-item" onClick={(e) => this.resolver(e, "PENDING")}>Pending</button>
                                </div>
                            </div>
                        </div>
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
                </>
            )
        }
    }
}