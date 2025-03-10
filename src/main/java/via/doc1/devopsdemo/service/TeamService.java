package via.doc1.devopsdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.doc1.devopsdemo.model.Task;
import via.doc1.devopsdemo.model.TeamMember;
import via.doc1.devopsdemo.repository.TaskRepository;
import via.doc1.devopsdemo.repository.TeamMemberRepository;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamMemberRepository teamRepository;

    @Autowired
    private TaskRepository taskRepository;

    // GET method to retrieve a TeamMember by ID
    public TeamMember getTeamMember(String memberId) {
        return teamRepository.findById(memberId).orElse(null);
    }

    // GET method to retrieve all Tasks associated with a TeamMember
    public List<Task> getTasks(String memberId) {
        TeamMember member = getTeamMember(memberId);
        return member == null ? null : member.getTasks();
    }

    // GET method to retrieve a specific Task for a TeamMember
    public Task getTask(String memberId, String taskId) {
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
        return teamRepository.save(teamMember);  // Save the TeamMember to the DB
    }

    // POST method to create and associate a Task with a TeamMember
    public Task createTaskForMember(String memberId, Task task) {
        TeamMember teamMember = getTeamMember(memberId);  // Retrieve the member
        if (teamMember != null) {
            task.setTeamMember(teamMember);  // Associate the task with the member
            return taskRepository.save(task);  // Save the Task to the DB
        }
        return null;  // If member not found, return null
    }
}
