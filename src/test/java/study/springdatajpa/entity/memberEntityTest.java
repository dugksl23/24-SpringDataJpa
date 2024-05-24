package study.springdatajpa.entity;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.repository.MemberRepository;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class memberEntityTest {


    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("base Entity test")
    @Transactional
    @Commit
    public void BaseEntity() {

        // given...
        Member member = new Member();
        memberRepository.save(member);

        // when...
        member.setMemberName("updated");
        memberRepository.flush();

        // then...
        Optional<Member> byId = memberRepository.findById(member.getId());
        if (byId.isPresent()) {
            Assertions.assertThat(byId.get().getCreatedDate()).isEqualTo(member.getCreatedDate());
            Assertions.assertThat(byId.get().getUpdatedDate()).isEqualTo(member.getUpdatedDate());
            log.info(" member created : {}", byId.get().getCreatedDate());
            log.info(" member updated : {}", byId.get().getUpdatedDate());
//            log.info(" member createdBy : {}", byId.get().getCreateBy());
//            log.info(" member updatedBy : {}", byId.get().getUpdateBy());

        }

    }

}
