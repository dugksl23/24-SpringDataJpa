package study.springdatajpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "member_age")
    private int age;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TeamMember> teamMembers = new ArrayList<>();

    public void addMember(Member member) {
        TeamMember memberTeam = new TeamMember(member, this);
        teamMembers.add(memberTeam);
        member.getTeamMembers().add(memberTeam);
    }

}
