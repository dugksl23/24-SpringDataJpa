package study.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
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
        // 2. 양방향 매핑이기에 memberTeam 을 member 에서도 저장.
        //    member 에서도 team 을 1:다로 호출할 비지니스 로직이기에
        teamMembers.add(memberTeam);
        // 3. 양항뱡 매핑이기에 team 에도 마찬가지로
        // 1:다 로 호출할 비지니스 로직을 위해 둘중에 한곳에 작성하면 된다.
        // 현재 정책상 팀에서 멤버를 추가하는 방향으로 정했다.

        //==========
        // 2024.5.19 team 엔티티에서 주석 처리 이유? 양방향 호출로 인해 무한이 된다.
        team.addMember(this);
    }


}
