package via.doc1.devopsdemo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import via.doc1.devopsdemo.model.TeamMember;
import via.doc1.devopsdemo.service.TeamService;

import java.util.Arrays;
import java.util.UUID;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jook
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TeamController.class)
@ActiveProfiles("test")
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    // Create a mock TeamMember with explicit ID setting
    TeamMember mockTeamMember = createMockTeamMember();
    
    private TeamMember createMockTeamMember() {
        TeamMember member = new TeamMember();
        member.setId(UUID.randomUUID().toString());
        member.setName("Test User");
        member.setEmail("test@example.com");
        member.setTasks(new ArrayList<>());
        return member;
    }
    
    @Test
    public void getTeamMembersTest() throws Exception {
        // Arrange
        Mockito.when(teamService.getAllTeamMembers())
                .thenReturn(Arrays.asList(mockTeamMember));
        
        // Act & Assert
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/members")
                .accept(MediaType.APPLICATION_JSON);
        
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void createTeamMemberTest() throws Exception {
        // Arrange
        Mockito.when(teamService.createTeamMember(any(TeamMember.class)))
                .thenReturn(mockTeamMember);
        
        // Act & Assert
        String teamMemberJson = "{\"name\":\"Test User\",\"email\":\"test@example.com\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(teamMemberJson)
                .accept(MediaType.APPLICATION_JSON);
        
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andReturn();
    }
}