import React, { useState, useEffect } from 'react';

const Odontologos = () => {

    const [odontologos, seteOdontologos] = useState([]);
    const [loading, setLoading] = useState(true);


    const fetchApi = async () => {
        const response = await fetch("http://localhost:8080/odontologos");
        const responseJSON = await response.json()
        seteOdontologos(responseJSON);
        setLoading(false);
    }

    useEffect(() => {
        fetchApi();
    }, []);


    return (
        <div>
            { loading ? <p>Loading Data...</p> : <h3>HOLAAAAAAAAAAAAA</h3> }
            {odontologos.map( odo => <h3>{odo.nombre}</h3>)}
        </div>
    );
}

export default Odontologos;
