package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface FriendService {

    public Friend relateTo(String email, Friend friend);

    List<User> searchFriendByName(String name);
}
