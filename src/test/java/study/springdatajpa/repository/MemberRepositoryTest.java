package study.springdatajpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;
import study.springdatajpa.service.MemberService;

import java.beans.Transient;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    private MemberService memberService;

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

}