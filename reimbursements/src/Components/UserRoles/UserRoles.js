import React from 'react';
import {Link} from 'react-router-dom';

export class UserRoles extends React.Component {
    constructor(props) {
        super(props);
        this.selectAdmin = this.selectAdmin.bind(this);
        this.selectUser = this.selectUser.bind(this);
    }
    componentDidMount() {
        if (window.sessionStorage.getItem("username") === null || window.sessionStorage.getItem("userRole") === "USER") {
            window.location.href = "/login";
        }
    }

    render() {
        return (
            <div>
                <Link to='/reimbursements'>
                    <button type="button" className="btn btn-outline-success" onClick={() => {this.selectUser()}}>User</button>
                </Link>
                <Link to='/reimbursements'>
                    <button type="button" className="btn btn-outline-success" onClick={() => {this.selectAdmin()}}>Admin</button>
                </Link>
            </div>
        )
    }

    selectAdmin() {
        window.sessionStorage.setItem("selectedUserRole", "ADMIN");
    }

    selectUser() {
        window.sessionStorage.setItem("selectedUserRole", "USER");
    }
}