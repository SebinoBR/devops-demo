import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';  // To handle dynamic route params
import axios from 'axios';

function TaskDetails() {
  const { memberId, taskId } = useParams();  // Get memberId and taskId from URL
  const [task, setTask] = useState(null);

  // Fetch the task details from the backend
  useEffect(() => {
    axios.get(`http://localhost:8080/members/${memberId}/tasks/${taskId}`)
      .then(response => {
        setTask(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the task details!", error);
      });
  }, [memberId, taskId]);

  // If the task is not yet fetched
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
