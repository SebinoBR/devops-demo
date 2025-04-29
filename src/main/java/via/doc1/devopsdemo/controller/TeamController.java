package via.doc1.devopsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.doc1.devopsdemo.model.TeamMember;
import via.doc1.devopsdemo.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "*")  // Allow requests from any origin
@RestController
@RequestMapping("/members")
public class TeamController {

    private final TeamService teamService;
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamMember> createTeamMember(@RequestBody TeamMember teamMember) {
    try {
        System.out.println("Received request to create team member: " + teamMember);
        TeamMember newMember = teamService.createTeamMember(teamMember);
        System.out.println("Successfully created team member: " + newMember);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    } catch (Exception e) {
        System.err.println("Error creating team member: " + e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // GET endpoint to fetch all TeamMembers
    @GetMapping
    public ResponseEntity<List<TeamMember>> getAllTeamMembers() {
        logger.info("Fetching all team members");
        return ResponseEntity.ok(teamService.getAllTeamMembers());
    }

    // GET endpoint to fetch a TeamMember by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamMemberById(@PathVariable String id) {
        logger.info("Fetching team member with id: {}", id);
        TeamMember teamMember = teamService.getTeamMember(id);
        if (teamMember == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Team member not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(teamMember);
    }
}