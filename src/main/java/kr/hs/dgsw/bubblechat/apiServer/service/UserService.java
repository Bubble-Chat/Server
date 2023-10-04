package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.AuthUser;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;

import java.util.List;

public interface UserService {

    Users getUsers();

    User addUser(User user);

    User getByEmail(String email);

    AuthUser getUserByToken(String provider, String token);

    Users findAllUserByName(String name);

    User changeProfile(String myEmail, User user);

    AuthUser signin(User User);

}
