package study.springdatajpa.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;
import study.springdatajpa.entity.TeamMember;
import study.springdatajpa.repository.MemberRepository;
import study.springdatajpa.repository.TeamMemberRepository;
import study.springdatajpa.repository.TeamRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class TeamService {

    public final TeamRepository teamRepository;
    public final MemberRepository memberRepository;
    public final TeamMemberRepository teamMemberRepository;

    @Transactional
    public Team createTeam(Team team) {
        teamRepository.save(team);
        return team;
    }

    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    @Transactional
    public TeamMember addMemberToTeam(Long teamId, Long memberId) {

        Optional<Team> team = teamRepository.findById(teamId);
        team.orElseThrow(() -> new IllegalArgumentException("Team does not exist"));

        Optional<Member> member = memberRepository.findById(memberId);
        member.orElseThrow(() -> new IllegalStateException("Member with id " + memberId + " not found"));

        TeamMember save = teamMemberRepository.save(new TeamMember(member.get(), team.get()));
        return save;
    }


}
