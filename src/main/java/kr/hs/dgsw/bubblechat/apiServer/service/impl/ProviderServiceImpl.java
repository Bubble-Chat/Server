package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "providerService")
@RequiredArgsConstructor
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String getEmailByToken(String provider, String token) {
        if ("google".equals(provider)) {
            return getEmailByGoogle(token);
        } else if ("naver".equals(provider)) {
            return getEmailByNaver(token);
        } else if ("kakao".equals(provider)) {
            return getEmailByKaKao(token);
        } else {
            throw new RuntimeException("unknown provider");
        }


    }

    private String getEmailByGoogle(String token) {
        // TODO

        return "abcd";
    }

    private String getEmailByNaver(String token) {
        // TODO

        return "abcd";
    }

    private String getEmailByKaKao(String token) {
        // TODO

        return "abcd";
    }
}
