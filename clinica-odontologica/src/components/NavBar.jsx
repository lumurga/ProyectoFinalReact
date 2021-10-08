import React, { useState } from 'react';
import "../Styles/Navbar.css"

const Navbar = () => {

    const cambioColor = (e) =>{
        e.target.className === "options" ? e.target.className = "optionsSelected" : e.target.className = "options"
    }

    return (
        <div className="navbarContainer">
            <div className="options" onClick={cambioColor}></div>
            <div className="options" onClick={cambioColor}></div>
            <div className="options" onClick={cambioColor}></div>
            <div className="options" onClick={cambioColor}></div>
            <div className="options" onClick={cambioColor}></div>
            <div className="options" onClick={cambioColor}></div>
        </div>
    );
}

export default Navbar;
