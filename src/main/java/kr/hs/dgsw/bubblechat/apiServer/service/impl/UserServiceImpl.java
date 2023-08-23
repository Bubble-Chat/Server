package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.exception.EmailNotFoundException;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.UserRepository;
import kr.hs.dgsw.bubblechat.apiServer.service.ProviderService;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service(value = "userService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    private final ProviderService providerService;

    @Override
    public User addUser(User user) {

        UserEntity entity = user.toEntity();
        UserEntity saved = userRepository.save(entity);

        if(!saved.equals(entity)) throw new RuntimeException("Cannot save user");
        return saved.toDTO();
    }

    @Override
    public User getByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException());

        return entity.toDTO();
    }

    @Override
    public User getUserByToken(String provider, String token) {
        String email = providerService.getEmailByToken(provider, token);

        User user = getByEmail(email);
        return user;
    }
}
