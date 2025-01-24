package worldcup.service;

import worldcup.models.User;

public interface UserService {
    User findOneByEmail(String email);
    User save(User user);
    User delete(Long id);
}
