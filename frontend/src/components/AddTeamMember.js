import React, { useState } from 'react';
import axios from 'axios';

// Create API with base URL
const api = axios.create({
  baseURL: process.env.REACT_APP_API_URL || 'http://localhost:8080'
});

const AddTeamMember = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newMember = { name, email };

        try {
            console.log('Sending request to:', `${api.defaults.baseURL}/members`);
            const response = await fetch(`${api.defaults.baseURL}/members`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newMember),
            });
            const data = await response.json();
            setMessage(`Member added successfully! ID: ${data.id}`);
            setName('');
            setEmail('');
        } catch (error) {
            console.error('Error details:', error);
            setMessage('Error adding member!');
        }
    };

    return (
        <div>
            <h2>Add Team Member</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name</label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Email</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Add Member</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default AddTeamMember;