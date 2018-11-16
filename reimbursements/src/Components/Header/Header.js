import React from 'react';
import {Link} from 'react-router-dom';
import {withRouter} from 'react-router';
import BackButton from './BackButton.js';
import {Switch, Route} from 'react-router-dom';

export class Header extends React.Component {
    constructor(props) {
        super(props);
        this.signOut = this.signOut.bind(this);
    }
    
    render() {
        return (
            <div>
                <nav className="navbar navbar-toggleable-md navbar-expand-lg navbar-light bg-light display-front nav-pad">
                    <div>
                        <BackButton />
                    </div>
                    <div>
                        <span className="label" id="header-label">Reimbursements</span>
                    </div>
                    <div>
                        {
                            (this.props.location.pathname !== '/' && this.props.location.pathname !== '/login') &&
                                <Link to="/login">
                                    <button type="button" id="header-right-button" className="btn btn-outline-danger" onClick={(event) => {this.signOut(event)}}>Sign Out</button>
                                </Link>
                        }
                    </div>
                </nav>
            </div>
        )
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
}

export default withRouter(
    Header
)