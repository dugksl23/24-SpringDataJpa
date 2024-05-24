package study.springdatajpa.repository.query;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberQueryDto {

    private Long id;
    private String memberName;
    private int age;

}
