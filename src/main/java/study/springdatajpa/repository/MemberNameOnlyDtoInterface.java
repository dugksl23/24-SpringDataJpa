package study.springdatajpa.repository;


import org.springframework.beans.factory.annotation.Value;

public interface MemberNameOnlyDtoInterface {

    //Open Projection
    @Value("#{target.memberName + '' + target.age}")
    String getUserNameAndAge();

    // Closed Projection
    String getMemberName();

}
