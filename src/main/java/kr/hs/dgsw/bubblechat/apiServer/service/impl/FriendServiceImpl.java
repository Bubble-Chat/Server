package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.entity.BuddyEntity;
import kr.hs.dgsw.bubblechat.apiServer.entity.FriendEntity;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.BuddyRepository;
import kr.hs.dgsw.bubblechat.apiServer.repository.FriendRepository;
import kr.hs.dgsw.bubblechat.apiServer.service.FriendService;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service(value = "friendService")
@RequiredArgsConstructor
@Slf4j
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    private final UserService userService;

    private final BuddyRepository buddyRepository;

    @Override
    public User relateTo(String email, Friend friend) {

        if(email.equals(friend.getFriendEmail())) {
            log.info("본인 친추!!");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "혼자 놀고 싶으신가요ㅠ..?");
        }

        if(isFriend(email, friend.getFriendEmail())) {
            log.info("중복 친추!!");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미 친구신데요!");
        }

        FriendEntity friendEntity = FriendEntity.builder()
                .email(email)
                .friendEmail(friend.getFriendEmail())
                .build();

        FriendEntity related = friendRepository.save(friendEntity);

        //log.info("[related] {}", related);

        User userEntity = userService.getByEmail(related.getEmail());

        return userEntity;

    }

    public Boolean isFriend(String me, String friend) {

        User user_friend = userService.getByEmail(friend);
        User user_me = userService.getByEmail(me);

        Optional<FriendEntity> foundEntity =
                friendRepository.findByEmailAndFriendEmail(user_me.getEmail(), user_friend.getEmail());

        return foundEntity.isPresent();

    }

    @Override
    public List<BuddyEntity> getBuddy(String email) {

        List<BuddyEntity> list = buddyRepository.findAllByEmail(email);


        return list;
    }
}
