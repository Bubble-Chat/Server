package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.AuthUser;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;

public interface UserService {

    public Users getUsers();

    public User addUser(User user);

    public User getByEmail(String email);

    public AuthUser getUserByToken(String provider, String token);

}
