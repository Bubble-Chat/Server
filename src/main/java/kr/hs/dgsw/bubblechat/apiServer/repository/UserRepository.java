package kr.hs.dgsw.bubblechat.apiServer.repository;

import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    public UserEntity findByName(String name);

}
