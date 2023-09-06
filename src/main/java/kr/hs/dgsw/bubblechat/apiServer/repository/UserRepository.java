package kr.hs.dgsw.bubblechat.apiServer.repository;

import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByName(String name);

    Optional<UserEntity> findByEmail(String email);

}
