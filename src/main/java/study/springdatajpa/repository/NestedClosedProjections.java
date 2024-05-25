package study.springdatajpa.repository;

import lombok.Getter;
import study.springdatajpa.entity.TeamMember;

import java.util.List;

public interface NestedClosedProjections {

        String getMemberName();
        List<TeamMember> getTeamMembers();

        interface TeamMembers {
            TeamMember getTeam();
        }



}