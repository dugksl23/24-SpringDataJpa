package study.springdatajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.springdatajpa.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
