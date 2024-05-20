package study.springdatajpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;
import study.springdatajpa.repository.query.MemberQueryDto;
import study.springdatajpa.service.MemberService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    @Transactional
//    @Rollback(false)
    @Commit
        // test code에서는 insert query 로그 안남음 -> rollback false
    void memberSaveTest() {

        // given...
        Member member = new Member("USER1", 00);

        // when...
        memberService.signUpMember(member); // 1차 캐싱 된 entity 자동 반환.
        log.info("saved member id : {}", member.getId());
        log.info("member value : {}", member);
        Member byId = memberService.findById(member.getId()).get();

        // jpa의 동일 트랜잭션에서는 5가지 특징
        // 1. 동일성 보장 = 1차 캐싱
        // 2. 변경 감지
        // 3. lazy loading
        // 4. 쓰기 지연 저장소

        // then...
        org.assertj.core.api.Assertions.assertThat(byId.getId()).isEqualTo(member.getId());

    }


    @Test
    void memberFindByIdTest() {
        log.info("memberRepository Value : {}", memberRepository.getClass());
        log.info("member count : {}", memberRepository.count());
    }

    @Test
    void findMemberTest() {

        Member member = new Member("USER1", 00);
        Member member1 = new Member("USER1", 01);
        Member member2 = new Member("USER123", 02);
        List<Member> list = Arrays.asList(member1, member, member2);

        memberRepository.saveAll(list);
        memberRepository.findByMemberNameAfter(member.getMemberName()).forEach(member3 -> {
            log.info("member name : {}", member3.getMemberName());
            log.info("member age : {}", member3.getAge());
        });
        memberRepository.findByMemberNameContaining(member.getMemberName());
//                .forEach(member3 -> {
//            log.info("member name : {}", member3.getMemberName());
//            log.info("member age : {}", member3.getAge());
//        });
//        memberRepository.findByMemberNameAndAgeGreaterThan(member.getMemberName(), member.getAge()).forEach(member3 -> {
//            log.info("member name : {}", member3.getMemberName());
//            log.info("member age : {}", member3.getAge());
//        });

    }


    @Test
    void findMemberBy() {

        Member member = new Member("USER1", 00);
        Member member1 = new Member("USER1", 01);
        Member member2 = new Member("USER123", 02);
        List<Member> list = Arrays.asList(member1, member, member2);
        memberRepository.saveAll(list);

        memberRepository.findMemberBy();
        memberRepository.findAll();
        memberRepository.findTop100MemberBy();
        memberRepository.findByMemberName("USER1");
        List<String> allByMemberName = memberRepository.findAllByMemberName();

        Assertions.assertThat(allByMemberName).hasSize(3);
    }


    @Test
    @Transactional
    @Commit
    void findAllByMemberDto() {

        // given...
        Member member = new Member("MEMBER1", 20);
        Member member1 = new Member("MEMBER2", 21);
        Member member2 = new Member("MEMBER3", 21);
        Member member3 = new Member("MEMBER4", 21);
        List<Member> list = Arrays.asList(member1, member, member2, member3);
        memberRepository.saveAll(list);

        Team team = new Team("team 1");
        Team team1 = new Team("team 2");
        Team team2 = new Team("team 3");
        List<Team> list1 = Arrays.asList(team1, team2, team);
        teamRepository.saveAll(list1);

        member.addTeam(team);
        member1.addTeam(team);
        member2.addTeam(team);
        member3.addTeam(team1);


        List<MemberQueryDto> membersByTeamId = memberRepository.findMembersByTeamId(team.getId());

        Assertions.assertThat(membersByTeamId).hasSize(3);

    }

    @Test
    void returnParameterTest(){

        Member member = new Member("MEMBER1", 20);
        Member member1 = new Member("MEMBER2", 21);
        Member member2 = new Member("MEMBER3", 21);
        Member member3 = new Member("MEMBER4", 21);
        List<Member> list1 = Arrays.asList(member, member1, member2, member3);
        memberRepository.saveAll(list1);

        Member byMemberName = memberRepository.findByMemberName(member.getMemberName());
        List<Member> allByAge = memberRepository.findAllByAge(21);
        Optional<List<Member>> byAge = memberRepository.findByAge(21);

        Assertions.assertThat(byMemberName.getMemberName()).isEqualTo(member.getMemberName());
        Assertions.assertThat(allByAge).hasSize(3);
        Assertions.assertThat(byAge.get().get(0).getAge()).isEqualTo(21);

    }
}