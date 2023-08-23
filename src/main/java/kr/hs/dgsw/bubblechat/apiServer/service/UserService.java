package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.User;

public interface UserService {

    public User addUser(User user);

    public User getByEmail(String email);

    public User getUserByToken(String provider, String token);

}
