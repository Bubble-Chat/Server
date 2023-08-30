package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.AuthUser;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.exception.EmailNotFoundException;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.UserRepository;
import kr.hs.dgsw.bubblechat.apiServer.security.JwtTokenProvider;
import kr.hs.dgsw.bubblechat.apiServer.service.ProviderService;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service(value = "userService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    private final ProviderService providerService;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User addUser(User user) {

        UserEntity entity = user.toEntity();
        UserEntity saved = userRepository.save(entity);

        if(!saved.equals(entity)) throw new RuntimeException("Cannot save user");
        return saved.toDTO();
    }

    @Override
    public User getByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findByEmail(email);

        if(entity.isEmpty()) return null;

        return entity.get().toDTO();
    }

    @Override
    public AuthUser getUserByToken(String provider, String token) {
        User foundUser = providerService.getUserByToken(provider, token);

        User user = getByEmail(foundUser.getEmail());

        if (user == null) {
            user = addUser(User.builder()
                    .email(foundUser.getEmail())
                    .name(foundUser.getName()).build());
        }

        String accessToken = jwtTokenProvider.generateToken(
                user.getEmail(),
                new SimpleGrantedAuthority("USER"),
                30L * 60L * 1000);

        return AuthUser.builder()
                .isExists(true)
                .name(user.getName())
                .email(user.getEmail())
                .accessToken(accessToken)
                .build();
    }


}
