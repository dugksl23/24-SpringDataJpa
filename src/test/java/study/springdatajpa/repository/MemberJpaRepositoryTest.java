package study.springdatajpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.springdatajpa.entity.Member;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    void findByName() {

        Member member = new Member("MEMBER1", 20);
        Member member1 = new Member("MEMBER2", 21);
        Member member2 = new Member("MEMBER3", 21);
        Member member3 = new Member("MEMBER4", 21);

        List<Member> list1 = Arrays.asList(member, member1, member2, member3);
        memberJpaRepository.saveAll(list1);
        int age = 21;
        int offset = 0;
        int limit = 10;

        List<Member> byPage = memberJpaRepository.findByPage(age, offset, limit);
        Long l = memberJpaRepository.totalCount(age);
        Assertions.assertEquals(byPage.size(), l);


    }
}