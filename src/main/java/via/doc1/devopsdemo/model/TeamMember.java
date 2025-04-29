package via.doc1.devopsdemo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Entity(name = "TeamMember")
@Table(name = "team_member")
public class TeamMember {

    @Id
    private String id;

    private String name;
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teamMember")
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();

    // This method will be called by JPA before persisting
    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public TeamMember() {
        // Empty constructor - don't generate UUID here
    }

    public TeamMember(String name, String email) {
        this.name = name;
        this.email = email;
        // Don't set ID here either
    }

    // Constructor for cases where you want to specify the ID
    public TeamMember(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return String.format(
                "TeamMember [" +
                "id=%s, " +
                "name=%s, " +
                "email=%s" +
                "tasks=%s]", id, name, email, tasks);
    }
}