package study.springdatajpa.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;


    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TeamMember> teamMembers = new ArrayList<>();


    public void addMember(Member member) {
        TeamMember teamMember = new TeamMember(member, this);
        teamMembers.add(teamMember);
        member.getTeamMembers().add(teamMember);
    }

    public Team(String name) {
        this.name = name;
    }
}
