package study.springdatajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.springdatajpa.entity.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.teamMembers tm join fetch tm.team where m.id = :memberId")
    Optional<Member> findByIdWithTeams(Long memberId);
}
