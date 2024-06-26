import { Link } from "react-router-dom";
import React from "react";

export default function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div className="navbar-nav d-flex justify-content-between w-100">
                    <Link className="nav-item nav-link active" to="/api/users">Users<span
                        className="sr-only"></span></Link>
                    <Link className="nav-item nav-link" to="/api/roles">Roles</Link>
                    <Link className="nav-item nav-link" to="/api/request_statuses">Request Statuses</Link>
                    <Link className="nav-item nav-link" to="/api/payments">Payments</Link>
                    <Link className="nav-item nav-link" to="/api/credit_type">Credit Types</Link>
                    <Link className="nav-item nav-link" to="/api/credit_requests">Credit Requests</Link>
                    <Link className="nav-item nav-link" to="/api/credits">Credits</Link>
                </div>
            </div>
        </nav>
    );
}