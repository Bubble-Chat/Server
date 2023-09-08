package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;

import java.util.ArrayList;
import java.util.List;

public interface FriendService {

    Friend relateTo(String email, Friend friend);

    Users findFriend(String name);
}
