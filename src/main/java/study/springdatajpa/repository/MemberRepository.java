package study.springdatajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.springdatajpa.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.teamMembers tm join fetch tm.team where m.id = :memberId")
    Optional<Member> findByIdWithTeams(Long memberId);

    public List<Member> findByMemberNameAfter(@Param("memberName") String memberName);

    public List<Member> findByMemberNameContaining(@Param("memberName") String memberName);

    public List<Member> findByMemberNameAndAgeGreaterThan(@Param("memberName") String memberName, @Param("member_age") Integer age);

    public List<Member> findMemberBy();

    public List<Member> findTop100MemberBy();


    @Query(name = "Member.findByMemberName")
    public List<Member> findByMemberName(String memberName);

}
