package study.springdatajpa.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberJpaRepository {

    private final EntityManager em;

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

    @Transactional
    public void saveAll(List<Member> member) {
        member.forEach(this::save);
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll() {
        String jpql = "select m from Member m";
        return em.createQuery(jpql, Member.class).getResultList();
    }


    public List<Member> findByPage(int age, int offset, int limit) {
        String jpql = "select m from Member m where m.age = :age order by m.memberName desc";
        return em.createQuery(jpql, Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public Long totalCount(int age) {
        String jpql = "select count(m) from Member m where m.age = :age";
        return em.createQuery(jpql, Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }


    /**
     * JPA Bulk Update
     */
    public int bulkUpdateAgePlus(int age) {
        String jpql = "update Member m set m.age = m.age + 1 where m.age = :age";
        int i = em.createQuery(jpql)
                .setParameter("age", age)
                .executeUpdate();

        em.flush();
        em.clear();
        return i;
    }

}
