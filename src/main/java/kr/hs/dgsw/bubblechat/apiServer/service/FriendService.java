package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.entity.BuddyEntity;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;

import java.util.List;

public interface FriendService {

    User relateTo(String email, Friend friend);

    Boolean isFriend(String me, String friend);

    public List<BuddyEntity> getBuddy(String email);
}
