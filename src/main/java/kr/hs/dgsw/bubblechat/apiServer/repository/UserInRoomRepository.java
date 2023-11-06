package kr.hs.dgsw.bubblechat.apiServer.repository;

import kr.hs.dgsw.bubblechat.apiServer.entity.UserInRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInRoomRepository extends JpaRepository<UserInRoomEntity, Long> {

    Optional<UserInRoomEntity> findByEmailAndRoomIdx(String email, Long roomIdx);

    List<UserInRoomEntity> findByEmail(String email);

}
