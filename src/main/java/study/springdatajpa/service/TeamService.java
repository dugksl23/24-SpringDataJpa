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

    @Transactional
    public Team createTeam(Team team) {
        teamRepository.save(team);
        return team;
    }

    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }


    @Transactional
    public Team addMemberToTeam(Long teamId, Long memberId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team does not exist"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member with id " + memberId + " not found"));

        team.addMember(member);

        return teamRepository.save(team);
    }


}
