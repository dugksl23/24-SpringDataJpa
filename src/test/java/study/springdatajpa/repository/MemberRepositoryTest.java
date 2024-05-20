package study.springdatajpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;
import study.springdatajpa.service.MemberService;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

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
    void findMemberTest(){

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
    void findMemberBy(){
//        memberRepository.findMemberBy();
//        memberRepository.findAll();
        memberRepository.findTop100MemberBy();
        memberRepository.findByMemberName("member");
    }
}