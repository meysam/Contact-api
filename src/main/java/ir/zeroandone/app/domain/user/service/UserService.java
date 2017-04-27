package ir.zeroandone.app.domain.user.service;


import ir.zeroandone.app.domain.user.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
