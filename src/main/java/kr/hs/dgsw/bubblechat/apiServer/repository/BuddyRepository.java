package kr.hs.dgsw.bubblechat.apiServer.repository;

import kr.hs.dgsw.bubblechat.apiServer.entity.BuddyEntity;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BuddyRepository extends JpaRepository<BuddyEntity, Long> {


    public List<BuddyEntity> findAllByEmail(String email);


}
