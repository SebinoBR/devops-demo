import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'; // Importing React Router components
import './App.css';
import TeamMemberList from './components/TeamMemberList'; // Import TeamMemberList
import TaskDetails from './components/TaskDetails'; // Import TaskDetails
import AddTeamMember from './components/AddTeamMember'; // Import AddTeamMember

function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <h1>DevOps Demo Application</h1>
        </header>
        <Routes>
          {/* Existing routes */}
          <Route path="/members" element={<TeamMemberList />} />
          <Route path="/members/:memberId/tasks/:taskId" element={<TaskDetails />} />

          {/* Route for adding a new member */}
          <Route path="/add-member" element={<AddTeamMember />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
