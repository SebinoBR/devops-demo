package via.doc1.devopsdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.doc1.devopsdemo.model.Task;
import via.doc1.devopsdemo.model.TeamMember;
import via.doc1.devopsdemo.repository.TaskRepository;
import via.doc1.devopsdemo.repository.TeamMemberRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamMemberRepository teamRepository;

    @Autowired
    private TaskRepository taskRepository;

    // GET method to retrieve a TeamMember by ID
    public TeamMember getTeamMember(String memberId) {  // Changed to String
        return teamRepository.findById(memberId).orElse(null);
    }

    // GET method to retrieve all Tasks associated with a TeamMember
    public List<Task> getTasks(String memberId) {  // Changed to String
        TeamMember member = getTeamMember(memberId);
        return member == null ? null : member.getTasks();
    }

    // GET method to retrieve a specific Task for a TeamMember
    public Task getTask(String memberId, Long taskId) {  // Changed to String
        TeamMember member = getTeamMember(memberId);
        if (member == null) {
            return null;
        }
        for (Task t : member.getTasks()) {
            if (t.getId().equals(taskId)) {
                return t;
            }
        }
        return null;
    }

    // POST method to save a new TeamMember to the database
    public TeamMember createTeamMember(TeamMember teamMember) {
        // Ensure ID is set before saving
        if (teamMember.getId() == null || teamMember.getId().isEmpty()) {
            teamMember.setId(UUID.randomUUID().toString());
        }
        return teamRepository.save(teamMember);
    }

    // POST method to create and associate a Task with a TeamMember
    public Task createTaskForMember(String memberId, Task task) {  // Changed to String
        TeamMember teamMember = getTeamMember(memberId);
        if (teamMember != null) {
            task.setTeamMember(teamMember);
            return taskRepository.save(task);
        }
        return null;
    }

    // GET method to retrieve all TeamMembers
    public List<TeamMember> getAllTeamMembers() {
        return teamRepository.findAll();
    }
}