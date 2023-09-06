package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.Token;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.entity.TokenEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.TokenRepository;
import kr.hs.dgsw.bubblechat.apiServer.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "tokenService")
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    public String getEmailByToken(String token) {

        Optional<TokenEntity> accessToken = tokenRepository.findByToken(token);

        if(accessToken.isEmpty()) {
            throw new RuntimeException("알 수 없는 토큰입니다.");
        }

        return accessToken.get().getEmail();

    }
}
