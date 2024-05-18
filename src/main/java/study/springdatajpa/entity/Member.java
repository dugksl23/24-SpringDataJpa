package study.springdatajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String memberName;

    public Member(String memberName) {
        this.id = id;
        this.memberName = memberName;
    }
}
