import React from 'react';
import {BiUser} from "react-icons/bi"
import "../Styles/Header.css"

const Header = () => {
    return (
        <div className="headerContainer">
            <p className="tituloHeader">Clínica Odontológica</p>
            <div className="usuarioContainer">
                <p className="nombreUsuario">Usuario</p>
                <BiUser style={{fontSize: 35, alignSelf: 'center'}} />
            </div>
        </div>
    );
}

export default Header;
