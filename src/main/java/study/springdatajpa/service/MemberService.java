package study.springdatajpa.service;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;
import study.springdatajpa.repository.MemberRepository;
import study.springdatajpa.repository.TeamMemberRepository;
import study.springdatajpa.repository.TeamRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final EntityManager em;

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


    @Transactional
    public Member addMemberToTeamV2(Team team, Member member) {
        member.addTeam(team);
        return memberRepository.save(member);
    }


    public Page<Member> findAllByPaging(int currentPage, int limit) {
        PageRequest pageRequest = PageRequest.of(currentPage, limit, Sort.by(Sort.Direction.ASC, "id"));
        return memberRepository.findAll(pageRequest);
    }

    public Member findReadOnlyByMemberName(String memberName) {
        return memberRepository.findReadOnlyByMemberName(memberName);
    }


    @Transactional
    public void memberBulkUpdate(int age){
        memberRepository.bulkUpdateAgePlus(age);

        em.flush();
        em.clear();

    }
}
