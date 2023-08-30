package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.User;

public interface ProviderService {

    public User getUserByToken(String provider, String token);

}
