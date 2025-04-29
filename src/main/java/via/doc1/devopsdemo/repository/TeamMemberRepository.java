package via.doc1.devopsdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.doc1.devopsdemo.model.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, String> {
    // No custom methods needed for now
}