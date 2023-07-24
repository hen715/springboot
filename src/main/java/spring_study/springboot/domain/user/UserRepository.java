package spring_study.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.springboot.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);//이미 생성된 계정인지 판단하는 메소드
}
