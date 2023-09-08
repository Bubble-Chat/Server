package kr.hs.dgsw.bubblechat.apiServer.repository;

import kr.hs.dgsw.bubblechat.apiServer.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

}
