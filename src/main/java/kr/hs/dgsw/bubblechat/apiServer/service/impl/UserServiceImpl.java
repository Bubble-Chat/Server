package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.bubblechat.apiServer.domain.AuthUser;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.domain.Users;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.UserRepository;
import kr.hs.dgsw.bubblechat.apiServer.security.JwtTokenProvider;
import kr.hs.dgsw.bubblechat.apiServer.service.ProviderService;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    private final ProviderService providerService;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Users getUsers() {

        List<UserEntity> foundUsers = userRepository.findAll();

        if(foundUsers.isEmpty()) {
            return null;
        }

        List<User> users = new ArrayList<User>();

        for(UserEntity user : foundUsers) {
            users.add(User.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .photoPath(user.getPhotoPath())
                    .build());
        }

        return Users.builder().userList(users).build();

    }

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

        if(entity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이메일을 다시 한 번 더 확인 해보세요!");
        }

        return entity.get().toDTO();
    }

    @Override
    public AuthUser signin(User user) {

        Optional<UserEntity> found = userRepository.findByEmail(user.getEmail());

        if(found.isEmpty()) {
            user = addUser(User.builder()
                    .name(user.getName())
                    .email(user.getEmail()).build());
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

    @Override
    public Users searchUser(String name) {

        List<UserEntity> foundUsers = userRepository.findAllByNameLike("%" + name + "%");

        if(foundUsers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "그런 친구는 읎다.");
        }

        List<User> users = new ArrayList<User>();
        for(UserEntity userEntity : foundUsers) {
            users.add(userEntity.toDTO());
        }

        return Users.builder().userList(users).build();

    }

    @Override
    @Transactional
    public User changeProfile(String email, User user) {
        UserEntity entity = userRepository.findByEmail(email).get();
        entity.setName(user.getName());
        entity.setPhotoPath(user.getPhotoPath());
        return entity.toDTO();
    }
}
