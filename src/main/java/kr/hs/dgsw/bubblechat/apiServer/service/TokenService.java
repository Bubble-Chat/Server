package kr.hs.dgsw.bubblechat.apiServer.service;

public interface TokenService {

    String getEmailByToken(String token);

}