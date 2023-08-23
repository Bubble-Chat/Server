package kr.hs.dgsw.bubblechat.apiServer.service;

public interface ProviderService {

    public String getEmailByToken(String provider, String token);

}
