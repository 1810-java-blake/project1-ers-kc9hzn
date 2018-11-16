import React from 'react';

import {Link} from 'react-router-dom';

export class SignOutButton extends React.Component {
    constructor(props) {
        super(props);
        this.signOut = this.signOut.bind(this);
    }

    signOut(event) {
        event.preventDefault();
        window.sessionStorage.setItem("id", null);
        window.sessionStorage.setItem("username", null);
        window.sessionStorage.setItem("password", null);
        window.sessionStorage.setItem("firstName", null);
        window.sessionStorage.setItem("lastName", null);
        window.sessionStorage.setItem("email", null);
        window.sessionStorage.setItem("userRole", null);
        window.sessionStorage.setItem("userObject", null);
        window.location.href = "/login";
    }

    render() {
        return (
            <div>
            {(this.props.location.pathname !== '/' && this.props.location.pathname !== '/login') &&
                                <Link to="/login">
                                    <button type="button" id="header-right-button" className="btn btn-outline-danger" onClick={(event) => {this.signOut(event)}}>Sign Out</button>
                                </Link>
            }
            </div>
        )
    }
}