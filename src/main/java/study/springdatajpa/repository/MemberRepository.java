package study.springdatajpa.repository;


import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;
import study.springdatajpa.repository.query.MemberQueryDto;

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

    public List<Member> findFirst100MemberBy();


//    @Query(name = "Member.findByMemberName")
//    public List<Member> findByMemberName(String memberName);

    @Query(value = "select m.memberName from Member m")
    public List<String> findAllByMemberName();


    // 특정 멤버가 속한 팀 전부 조회
    @Query("select t from Member m" + " join m.teamMembers tm" + " join tm.team t" + " where t.id = :teamId")
    List<Team> findTeamsByMemberId(@Param("teamId") Long teamId);

    //특정 팀에 속한 멤버 조회
    @Query("SELECT new study.springdatajpa.repository.query.MemberQueryDto(m.id, m.memberName, m.age) FROM Member m JOIN m.teamMembers tm JOIN tm.team t WHERE t.id = :teamId")
    public List<MemberQueryDto> findMembersByTeamId(@Param("teamId") Long teamId);

    @Query("select m from Member m where m.age in :ages")
    public List<Member> findByMemberAge(@Param("ages") List<Integer> ages);


    public List<Member> findAllByAge(@Param("age") Integer age);

//    public Member findByMemberName(String memberName);

    public Optional<List<Member>> findByAge(@Param("age") Integer age);


    @Query(value = "select m from Member m" +
            " left join fetch m.teamMembers tm" +
            " left join fetch tm.team t",
            countQuery = "select count(m) from Member m")
    Page<Member> findAll(Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    public int bulkUpdateAgePlus(@Param("age") int age);


    @Query(value = "select m from Member m" +
            " left join fetch m.teamMembers tm" +
            " left join fetch tm.team t")
    public List<Member> findMemberFetchJoin();


    @Override
    @EntityGraph(attributePaths = {"teamMembers"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"teamMembers"})
    @Query("select m from Member m where m.memberName = :memberName")
    Member findByMemberName(@Param("memberName") String memberName);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByMemberName(@Param("memberName") String memberName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findByUsername(String name);

}
