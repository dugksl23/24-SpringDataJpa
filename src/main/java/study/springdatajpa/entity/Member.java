package study.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String memberName;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TeamMember> teamMembers = new ArrayList<>();

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public void addTeam(Team team) {
        TeamMember memberTeam = new TeamMember(this, team);
        teamMembers.add(memberTeam);
        team.getTeamMembers().add(memberTeam);
    }



}
