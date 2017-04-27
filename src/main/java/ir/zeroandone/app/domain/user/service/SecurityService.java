package ir.zeroandone.app.domain.user.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
