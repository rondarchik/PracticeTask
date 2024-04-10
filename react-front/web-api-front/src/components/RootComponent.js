import Navbar from "./NavBarComponent";
import React from "react";
import { Outlet } from "react-router-dom";

export default function Root() {
    return (
        <main className="App">
            <header className="App-header">
                <Navbar/>
            </header>
            <Outlet />
        </main>
    );
}