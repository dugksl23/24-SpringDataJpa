package study.springdatajpa.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;
import study.springdatajpa.entity.TeamMember;
import study.springdatajpa.repository.MemberRepository;
import study.springdatajpa.repository.TeamMemberRepository;
import study.springdatajpa.repository.TeamRepository;

import java.util.Optional;

@SpringBootTest
@Slf4j
class TeamServiceTest {

    @Autowired
    private TeamService teamService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private TeamMemberRepository teamMemberService;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Test
    @Transactional
    @Commit
    void signUpMember() {

        // given...
        Member member = new Member("MEMBER1", 20);

        // when...
        Member member1 = memberService.signUpMember(member);

        // then...
        Assertions.assertThat(member1.getId()).isEqualTo(member.getId());
    }

    @Test
    @Transactional
    @Commit
    void createTeam() {
        // given...
        Team team = new Team("team 1");

        // when...
        Team team1 = teamService.createTeam(team);

        // then...
        Assertions.assertThat(team1.getId()).isEqualTo(team.getId());

    }

    @Test
    @Transactional
    @Commit
    void addMemberToTeam() {

        // given...
        Member member = new Member("MEMBER1", 20);
        memberService.signUpMember(member);
        Team team = new Team("team 1");
        teamService.createTeam(team);


        // when...
//        team.addMember(member);
        TeamMember teamMember = teamService.addMemberToTeam(team.getId(), member.getId());
        // cascade 옵션으로 insert query 생성

        // then..
        Member member1 = memberService.findById(member.getId()).get();
        Team team1 = teamService.findById(team.getId()).get();
        TeamMember teamMember1 = teamMemberRepository.findById(teamMember.getId()).get();
        Assertions.assertThat(member1.getId()).isEqualTo(member.getId());
        Assertions.assertThat(team1.getId()).isEqualTo(team.getId());
        Assertions.assertThat(teamMember1.getMember().getId()).isEqualTo(member1.getId());
        Assertions.assertThat(teamMember1.getTeam().getId()).isEqualTo(team1.getId());
    }
}