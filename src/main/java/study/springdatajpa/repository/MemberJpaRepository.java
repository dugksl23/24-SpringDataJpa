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
public class MemberJpaRepository {

    private final EntityManager em;

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Long count(){
        String jpql = "select count(m) from Member m";
        return em.createQuery(jpql, Long.class).getSingleResult();
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll(){
        String jpql = "select m from Member m";
        return em.createQuery(jpql, Member.class).getResultList();
    }
}
