package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.Relation;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;

import java.util.ArrayList;

public interface FriendService {

    public Relation relateTo(Relation relation);

    ArrayList<User> searchFriend(Relation relation);
}
