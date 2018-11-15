import React from 'react';
import {Link} from 'react-router-dom';
import {withRouter} from 'react-router';

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
                        {
                            (this.props.location.pathname !== '/') &&
                                <Link to="/">
                                    <button type="button" className="btn btn-outline-dark" onClick={() => { this.props.history.goBack() }}>Back</button>
                                </Link>
                        }
                    </div>
                    <div>
                        <span className="label">Reimbursements</span>
                    </div>
                    <div>
                        {
                            (this.props.location.pathname !== '/') &&
                                <Link to="/login">
                                    <button type="button" className="btn btn-outline-danger" onClick={(event) => {this.signOut(event)}}>Sign Out</button>
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
        window.sessionStorage.setItem("firstName", null);
        window.sessionStorage.setItem("lastName", null);
        window.sessionStorage.setItem("userRole", null);
        window.location.href = "/login";
    }
}

export default withRouter(
    Header
)