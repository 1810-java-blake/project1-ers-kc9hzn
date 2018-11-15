import React from 'react';

const axios = require('axios');

export class AddReimbursement extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            amount: 0,
            description: '',
            reimbursementType: ''
        }

        this.handleAmount = this.handleAmount.bind(this);
        this.handleDescription = this.handleDescription.bind(this);
        this.handleReimbursementType = this.handleReimbursementType.bind(this);
    }

    handleAmount(e) {
        this.setState({amount: e.target.value});
    }

    handleDescription(e) {
        this.setState({description: e.target.value});
    }

    handleReimbursementType(e, type) {
        e.preventDefault();
        this.setState({reimbursementType: type});
    }

    handleSubmit = (event) => {
        console.log(window.sessionStorage.getItem("userObject"));
        event.preventDefault();
        axios({
            method: 'post',
            url: 'http://localhost:8080/Project1/reimbursements',
            data: {
                id: 0,
                amount: this.state.amount,
                submitted: null,
                description: this.state.description,
                receipt: null,
                author: {
                    id: window.sessionStorage.getItem("id"),
                    username: window.sessionStorage.getItem("username"),
                    password: window.sessionStorage.getItem("password"),
                    firstName: window.sessionStorage.getItem("firstName"),
                    lastName: window.sessionStorage.getItem("lastName"),
                    email: window.sessionStorage.getItem("email"),
                    userRole: window.sessionStorage.getItem("userRole")
                },
                resolver: null,
                status: "PENDING",
                type: this.state.reimbursementType
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
            <form>
                <div className='form-group'>
                    <label htmlFor='amountInput'>Amount</label>
                    <input type='number' className='form-control' id='amountInput' placeholder='$00.00' value={this.state.amount} onChange={(e) => this.handleAmount(e)}/>
                </div>
                <div className='form-group'>
                    <label htmlFor='descriptionInput'>Description</label>
                    <input type='text' className='form-control' id='descriptionInput' placeholder='Description' value={this.state.description} onChange={(e) => this.handleDescription(e)}/>
                </div>
                <div className='form-group'>
                    <div className="dropdown">
                        <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Reimbursement Type
                        </button>
                        <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <button className="dropdown-item" onClick={(e) => this.handleReimbursementType(e, "LODGING")}>Lodging</button>
                            <button className="dropdown-item" onClick={(e) => this.handleReimbursementType(e, "TRAVEL")}>Travel</button>
                            <button className="dropdown-item" onClick={(e) => this.handleReimbursementType(e, "FOOD")}>Food</button>
                            <button className="dropdown-item" onClick={(e) => this.handleReimbursementType(e, "OTHER")}>Other</button>
                        </div>
                    </div>
                </div>
                <div className='form-group'>
                    {/* <Link to='/reimbursements'> */}
                        <button type="button" className="btn btn-primary" onClick={this.handleSubmit}>Submit</button>
                    {/* </Link> */}
                </div>
            </form>
        )
    }
}