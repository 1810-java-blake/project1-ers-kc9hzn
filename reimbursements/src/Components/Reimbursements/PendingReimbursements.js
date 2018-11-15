import React from 'react';
import {ResolverReimbursementTableCell} from './ResolverReimbursementTableCell.js';
import {Link} from 'react-router-dom';
const axios = require('axios');


export class PendingReimbursements extends React.Component {
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
                axios({
                    method: 'GET',
                    url: `http://localhost:8080/Project1/reimbursements/status/1`
                })
                .then((response) => {
                    this.setState({
                        reimbursements: response.data
                    })
                    console.log(response.data);
                });
        }
    }

    render() {
            return (
                <>
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
