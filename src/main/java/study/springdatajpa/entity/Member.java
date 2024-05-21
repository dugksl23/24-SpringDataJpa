package study.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@NamedQuery(
        name="Member.findByMemberName",
        query="select m from Member m where m.memberName = :memberName")
//@Setter(AccessLevel.PACKAGE)
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_age")
    private int age;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // cascade 옵션의 대상은 teamMember table
    private List<TeamMember> teamMembers = new ArrayList<>();

    public Member(String memberName, int age) {
        this.memberName = memberName;
        this.age = age;
    }

    public void addTeam(Team team) {
        // 1. 조인 테이블의 entity pk 값 저장을 위해 객체 전달
        TeamMember memberTeam = new TeamMember(this, team);
        // 2. 연관관계 편의를 위해서 양방향 매핑을 위해 memberTeam 조인테이블로
        // member 와 team 을 에서도 저장.
        teamMembers.add(memberTeam);
        team.getTeamMembers().add(memberTeam);
        // 3. member 를 save() 하면, cascade 속성으로 모두 저장됨.
    }

    // === 연관관계 메서드 ===
    // 해당 객체 뿐 아니라, 상대 객체의 값도 업데이트해주어야 한다.
    public void changeTeam(Team team){
        this.getTeamMembers().add(new TeamMember(this, team));
        team.addMember(this);

    }


}
