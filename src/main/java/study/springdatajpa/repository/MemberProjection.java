package study.springdatajpa.repository;

import lombok.Getter;

public interface MemberProjection {

    Long getId();

    String getMemberName();

    String getTeamName();

}
