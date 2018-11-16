import React from 'react';
import {Link} from 'react-router-dom';

const axios = require('axios');

export class Login extends React.Component {
    constructor(props) {
        super(props);
        this.handleUsername = this.handleUsername.bind(this);
        this.handlePassword = this.handlePassword.bind(this);
        this.state = {
            username: '',
            password: '',
            user: '',
            userObject: {}
        }
    }

    handleUsername(e) {
        this.setState({username: e.target.value});
    }

    handlePassword(e) {
        this.setState({password: e.target.value});
    }

    render() {
        return (
            <form>
                <div className='form-group'>
                    <label htmlFor='usernameInput'>Username</label>
                    <input type='text' className='form-control' id='usernameInput' placeholder='Username' value={this.state.username} onChange={(e) => this.handleUsername(e)}/>
                </div>
                <div className='form-group'>
                    <label htmlFor='passwordInput'>Password</label>
                    <input type='password' className='form-control' id='passwordInput' placeholder='Password' value={this.state.password} onChange={(e) => this.handlePassword(e)}/>
                </div>
                <div className='form-group'>
                    <button type="button" className="btn btn-primary" onClick={this.login}>Sign In</button>
                </div>
            </form>
        )
    }

    login = (event) => {
        event.preventDefault();
        axios({
            method: 'post',
            url: 'http://localhost:8080/Project1/users/login',
            data: {
                username: this.state.username,
                password: this.state.password
            }
        })
        .then(function (response) {
            console.log(response.data);
            window.sessionStorage.setItem("id", response.data.id);
            window.sessionStorage.setItem("username", response.data.username);
            window.sessionStorage.setItem("password", response.data.password);
            window.sessionStorage.setItem("firstName", response.data.firstName);
            window.sessionStorage.setItem("lastName", response.data.lastName);
            window.sessionStorage.setItem("email", response.data.email);
            window.sessionStorage.setItem("userRole", response.data.userRole);

            window.sessionStorage.setItem("userObject", response.data);

            // console.log("Welcome " + response.data.username);
            if (response.data.userRole === "ADMIN") {
                window.location.href = "/userRoles";
            } else {
                window.location.href = "/reimbursements";
            }
        });
    }
}