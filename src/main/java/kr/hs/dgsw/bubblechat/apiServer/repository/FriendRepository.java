package kr.hs.dgsw.bubblechat.apiServer.repository;

import kr.hs.dgsw.bubblechat.apiServer.entity.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FriendRepository extends JpaRepository<RelationEntity, Long> {

    public RelationEntity findByIdAndFriendId(String id, String friend_id);
    public ArrayList<RelationEntity> findAllByIdAndFriendName(String id, String friend_name);

}
