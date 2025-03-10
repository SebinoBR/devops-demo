import React, { useState } from 'react';
import axios from 'axios';

const AddTeamMember = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newMember = {
            name: name,
            email: email
        };

        try {
            await axios.post('http://localhost:8080/members', newMember);
            setMessage('Member added successfully!');
            setName('');
            setEmail('');
        } catch (error) {
            setMessage('Error adding member!');
            console.error('Error adding member:', error);
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
