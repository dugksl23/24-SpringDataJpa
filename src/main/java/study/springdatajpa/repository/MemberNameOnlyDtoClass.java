package study.springdatajpa.repository;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.processing.Generated;

@Getter
public class MemberNameOnlyDtoClass {

    private int age;
    private String memberName;

    public MemberNameOnlyDtoClass(int age, String memberName) {
        this.age = age;
        this.memberName = memberName;
    }
}
