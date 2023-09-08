package kr.hs.dgsw.bubblechat.apiServer.repository;

import kr.hs.dgsw.bubblechat.apiServer.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    FriendEntity findAllByEmail(String email);

    Optional<FriendEntity> findByEmailAndFriendEmail(String email, String friendEmail);

}
