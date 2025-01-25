package worldcup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.models.User;
import worldcup.repository.UserRepository;
import worldcup.service.UserService;

@Service
public class JpaUserService implements UserService {
    private final UserRepository repository;

    @Autowired
    public JpaUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findOneByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User delete(Long id) {
        User foundUser = repository.getReferenceById(id);
        repository.deleteById(foundUser.getId());
        return foundUser;
    }
}
