package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.Friend;

public interface FriendService {

    Friend relateTo(String email, Friend friend);

}
