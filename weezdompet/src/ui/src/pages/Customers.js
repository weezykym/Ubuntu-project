import React, {useLayoutEffect, useState} from 'react';

const customers = [
    {
        "id": 1,
        "firstName": "Cally",
        "lastName": "Reynolds",
        "emailAddress": "penatibus.et@lectusa.com",
        "phoneNumber": "(901) 166-8355",
        "address": "556 Lakewood Park, Bismarck, ND 58505"
    },
    {
        "id": 2,
        "firstName": "Sydney",
        "lastName": "Bartlett",
        "emailAddress": "nibh@ultricesposuere.edu",
        "phoneNumber": "(982) 231-7357",
        "address": "4829 Badeau Parkway, Chattanooga, TN 37405"
    }];

const Customers = () => {
    const [customers, setCustomers] = useState([]);

    useLayoutEffect(() => {
        const getCustomers = async() => {
            const res = await fetch('/api/customers');
            const customers = await res.json();
            setCustomers(customers);
        }
        getCustomers().catch(e => {
            console.log('error fetching customers: ' + e);
        })
    })
    return (
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email Address</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                </tr>
            </thead>
            <tbody>
                {customers.map(customer => {
                    const {
                        id,
                        firstName,
                        lastName,
                        emailAddress,
                        phoneNumber,
                        address
                    } = customer;
                    return (
                        <tr key={id} >
                            <td>{id}</td>
                            <td>{firstName}</td>
                            <td>{lastName}</td>
                            <td>{emailAddress}</td>
                            <td>{phoneNumber}</td>
                            <td>{address}</td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}

export default Customers;