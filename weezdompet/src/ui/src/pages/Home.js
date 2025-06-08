import React from "react";
import {Link} from "react-router-dom";

const Home = () => {
    return (
        <>
            <h3>Admin</h3>
            <ul style={{liststyleType: 'none'}}>
                <li>
                    <Link to={'/customers'}>Customers</Link>
                    <Link to={'/products'}>Products</Link>
                    <Link to={'/services'}>Services</Link>
                    <Link to={'/vendors'}>Vendors</Link>
                </li>
            </ul>
        </>
    );
};

export default Home;