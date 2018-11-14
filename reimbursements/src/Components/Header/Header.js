import React from 'react';
import {Link} from 'react-router-dom';
import {BrowserRouter} from 'react-router-dom';

export class Header extends React.Component {
    render() {
        return (
            <div>
                <nav className="navbar navbar-toggleable-md navbar-expand-lg navbar-light bg-light display-front nav-pad">
                    <div>
                        <BrowserRouter>
                            <Link to="/">
                                <button id="home" type="button" class="btn btn-outline-dark">Back</button>
                            </Link>
                        </BrowserRouter>
                    </div>
                    <div>
                        <span className="label">Reimbursements</span>
                    </div>
                    <div>
                        <a className="btn btn-primary" href="/" role="button">Sign Out</a>
                    </div>
                </nav>
            </div>
        )
    }
}