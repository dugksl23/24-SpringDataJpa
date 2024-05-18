package study.springdatajpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
