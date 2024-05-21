package study.springdatajpa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.springdatajpa.entity.Member;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findAllByMemberName(String memberName) {
        String jpql = "select m from Member m where m.memberName = :memberName";
        return em.createQuery(jpql)
                .setParameter("memberName", memberName)
                .getResultList();
    }
}
