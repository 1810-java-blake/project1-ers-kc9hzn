import React from 'react';
import {Link} from 'react-router-dom';
import {withRouter} from 'react-router';

export class BackButton extends React.Component {
    render() {
        return (
            <div>
            {(this.props.location.pathname === "/login" || this.props.location.pathname === "/" || window.location.pathname === "/userRoles")
                ? <button disabled={true} type="button" id="header-left-button" className="btn btn-outline-dark" onClick={() => { this.props.history.goBack() }}>Back1</button>
                : ((this.props.location.pathname === "/reimbursements") && (window.sessionStorage.getItem("userRole") === "USER")
                ? <button disabled={true} type="button" id="header-left-button" className="btn btn-outline-dark" onClick={() => { this.props.history.goBack() }}>Back2</button>
                : <button type="button" id="header-left-button" className="btn btn-outline-dark" onClick={() => { this.props.history.goBack() }}>Back3</button>)
            }
            </div>
        )
    }
}

export default withRouter(
    BackButton
)