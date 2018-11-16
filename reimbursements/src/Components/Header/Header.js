import React from 'react';
import {Link} from 'react-router-dom';
import {withRouter} from 'react-router';
import BackButton from './BackButton.js';
import {Switch, Route} from 'react-router-dom';
import {HeaderLabel} from './HeaderLabel.js';
import {SignOutButton} from './SignOutButton.js';

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
                        <HeaderLabel />
                    </div>
                    <div>
                        <SignOutButton />
                    </div>
                </nav>
            </div>
        )
    }
}

export default withRouter(
    Header
)