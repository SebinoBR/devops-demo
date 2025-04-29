import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const TeamMemberList = () => {
    const [teamMembers, setTeamMembers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get('/members')  // <-- no localhost:8080
            .then(response => {
                console.log("Fetched team members:", response.data);
                setTeamMembers(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching team members:', error);
                setError('Error fetching data');
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <div>Loading team members...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    return (
        <div>
            <h1>Team Members</h1>
            <ul>
                {teamMembers.map((member) => (
                    <li key={member.id}>
                        <h3>{member.name} - {member.email}</h3>
                        <ul>
                            {member.tasks && member.tasks.length > 0 ? (
                                member.tasks.map((task) => (
                                    <li key={task.id}>
                                        <Link to={`/members/${member.id}/tasks/${task.id}`}>
                                            {task.name} - {task.description}
                                        </Link>
                                    </li>
                                ))
                            ) : (
                                <li>No tasks assigned</li>
                            )}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TeamMemberList;
