import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';  // Make sure to import Link for routing

const TeamMemberList = () => {
    const [teamMembers, setTeamMembers] = useState([]);

    useEffect(() => {
        // Fetching the team members from the backend
        axios.get('http://localhost:8080/members')
            .then(response => {
                setTeamMembers(response.data);
            })
            .catch(error => {
                console.error('Error fetching team members:', error);
            });
    }, []);

    return (
        <div>
            <h1>Team Members</h1>
            <ul>
                {teamMembers.map((member) => (
                    <li key={member.id}>
                        {/* Link to the tasks page for the specific team member */}
                        <h3>{member.name} - {member.email}</h3>
                        {/* Check if the member has tasks */}
                        <ul>
                            {member.tasks && member.tasks.map((task) => (
                                <li key={task.id}>
                                    <Link to={`/members/${member.id}/tasks/${task.id}`}> {/* Dynamically set taskId */}
                                        {task.name} - {task.description}
                                    </Link>
                                </li>
                            ))}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TeamMemberList;
