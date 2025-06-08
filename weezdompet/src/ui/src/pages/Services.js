import React, {useLayoutEffect, useState} from 'react';
import {getCurrency} from '../Utils';

export const Services = () => {
    const [services, setServices] = useState([]);

    useLayoutEffect(() => {
        const getServices = async() => {
            const res = await fetch('api/services');
            const services = res.json();
            setServices(services);
        }
        getServices().catch(e => {
            console.log("error fetching services: " + e);
        },  [])

        return (
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                {services.map(service => {
                    const {
                        serviceId,
                        name,
                        price
                    } = service;
                    return (
                        <tr key={service}>
                            <td>{serviceId}</td>
                            <td>{name}</td>
                            <td>{price}</td>
                        </tr>
                    )
                })}
                </tbody>
            </table>
        )
    })
}