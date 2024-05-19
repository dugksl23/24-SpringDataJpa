package study.springdatajpa.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;
import study.springdatajpa.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
//    private final MemberJpaRepository MemberRepository;

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

}
