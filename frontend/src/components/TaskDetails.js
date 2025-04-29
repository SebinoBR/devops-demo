import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function TaskDetails() {
    const { memberId, taskId } = useParams();
    const [task, setTask] = useState(null);

    useEffect(() => {
        axios.get(`/members/${memberId}/tasks/${taskId}`)  // <-- no localhost:8080
            .then(response => {
                setTask(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the task details!", error);
            });
    }, [memberId, taskId]);

    if (!task) {
        return <div>Loading...</div>;
    }

    return (
        <div className="task-details">
            <h2>Task Details</h2>
            <p><strong>Task Name:</strong> {task.name}</p>
            <p><strong>Description:</strong> {task.description}</p>
            <p><strong>Assigned to:</strong> {task.teamMember.name}</p>
            <p><strong>Email:</strong> {task.teamMember.email}</p>
        </div>
    );
}

export default TaskDetails;
