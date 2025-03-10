package via.doc1.devopsdemo.controller;

import org.springframework.web.bind.annotation.*;
import via.doc1.devopsdemo.model.Task;
import via.doc1.devopsdemo.model.TeamMember;
import via.doc1.devopsdemo.service.TeamService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/members")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // GET endpoint to retrieve TeamMember by memberId
    @GetMapping("/{memberId}")
    public TeamMember getTeamMember(@PathVariable String memberId) {
        return teamService.getTeamMember(memberId);
    }

    // GET endpoint to retrieve Task details by memberId and taskId
    @GetMapping("/{memberId}/tasks/{taskId}")
    public Task getTaskDetails(@PathVariable String memberId,
                               @PathVariable String taskId) {
        return teamService.getTask(memberId, taskId);
    }

    // POST endpoint to create a new TeamMember
    @PostMapping("/")
    public TeamMember createTeamMember(@RequestBody TeamMember teamMember) {
        return teamService.createTeamMember(teamMember);  // Create TeamMember
    }

    // POST endpoint to create a new Task for a specific member
    @PostMapping("/{memberId}/tasks")
    public Task createTaskForMember(@PathVariable String memberId, @RequestBody Task task) {
        return teamService.createTaskForMember(memberId, task);  // Create Task for Member
    }
}
