package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.Relation;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.entity.RelationEntity;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.FriendRepository;
import kr.hs.dgsw.bubblechat.apiServer.repository.UserRepository;
import kr.hs.dgsw.bubblechat.apiServer.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "friendService")
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    public Relation relateTo(Relation relation) {

        RelationEntity relationEntity = relation.toEntity();
        RelationEntity saved = friendRepository.save(relationEntity);

        if(!saved.equals(relationEntity)) throw new RuntimeException("Cannot save relation");
        return saved.toDTO();

    }

    @Override
    public ArrayList<User> searchFriend(Relation rel) {

        ArrayList<User> users = new ArrayList<User>();

        if(rel.getFriend_id() == null) {
            if(rel.getFriend_name() == null) throw new RuntimeException("Put the friend_name");

            ArrayList<RelationEntity> relations = friendRepository.findAllByIdAndFriendName(rel.getId(), rel.getFriend_name());

            if(relations.isEmpty()) throw new RuntimeException("비었습니다.");

            for(RelationEntity relation : relations) {
                Optional<UserEntity> found = userRepository.findById(relation.getFriendId());
                if(found.isEmpty()) throw new RuntimeException("Cannot found users by name");
                users.add(found.get().toDTO());
            }
            return users;
        }
        else {
            RelationEntity relations = friendRepository.findByIdAndFriendId(rel.getId(), rel.getFriend_id());

            if(relations == null) return null;
            Optional<UserEntity> user = userRepository.findById(relations.getFriendId());

            if(user.isEmpty()) throw new RuntimeException("Cannot found user by id");
            users.add(user.get().toDTO());
        }

        return users;

    }
}
