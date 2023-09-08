package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;

public interface FriendService {

    Friend relateTo(String email, Friend friend);

    Users findFriend(String name);
}
