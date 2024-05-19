package study.springdatajpa.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;
import study.springdatajpa.repository.MemberJpaRepository;
import study.springdatajpa.repository.MemberRepository;
import study.springdatajpa.repository.TeamRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Member signUpMember(Member member) {
        memberRepository.save(member);
        return member;
    }


    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public Optional<Member> findByIdWithTeams(Long memberId) {
        return memberRepository.findByIdWithTeams(memberId);
    }

    @Transactional
    public Member addMemberToTeam(Long teamId, Long memberId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team does not exist"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member with id " + memberId + " not found"));

        member.addTeam(team);

        return memberRepository.save(member);
    }



}
