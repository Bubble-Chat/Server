package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;
import kr.hs.dgsw.bubblechat.apiServer.entity.FriendEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.FriendRepository;
import kr.hs.dgsw.bubblechat.apiServer.service.FriendService;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service(value = "friendService")
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    private final UserService userService;

    @Override
    public Friend relateTo(String email, Friend friend) {

        if(email.equals(friend.getFriendEmail())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "혼자 놀고 싶으신가요ㅠ..?");
        }

        if(isFriend(email, friend.getFriendEmail())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미 친구신데요!");
        }

        FriendEntity friendEntity = FriendEntity.builder()
                .email(email)
                .friendEmail(friend.getFriendEmail())
                .build();

        FriendEntity related = friendRepository.save(friendEntity);

        return Friend.builder()
                .email(related.getEmail())
                .friendEmail(related.getFriendEmail())
                .build();

    }

    public Users findFriend(String name) {
        return Users.builder().userList(userService.findUserByName(name)).build();
    }

    private boolean isFriend(String me, String friend) {

        User user_friend = userService.getByEmail(friend);
        User user_me = userService.getByEmail(me);

        Optional<FriendEntity> foundEntity =
                friendRepository.findByEmailAndFriendEmail(user_me.getEmail(), user_friend.getEmail());

        return foundEntity.isPresent();

    }

}
