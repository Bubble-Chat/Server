package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.UserRepository;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service(value = "userService")
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {

        String id = makeNewId();

        UserEntity entity = UserEntity.builder()
                .id(id)
                .email(user.getEmail())
                .name(user.getName())
                .photoPath(user.getPhotoPath())
                .backgroundPath(user.getBackgroundPath())
                .build();


        userRepository.save(entity);

        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            UserEntity entity1 = optional.get();
            return User.builder()
                    .id(entity1.getId())
                    .email(entity1.getEmail())
                    .name(entity1.getName())
                    .photoPath(entity1.getPhotoPath())
                    .backgroundPath(entity1.getBackgroundPath())
                    .build();
        } else {
            throw new RuntimeException("Cannot save user");
        }
    }

    private String makeNewId() {
        return UUID.randomUUID().toString();
    }
}
