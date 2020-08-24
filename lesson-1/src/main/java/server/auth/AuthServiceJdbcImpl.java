package server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.User;
import server.persistance.UserRepository;

import java.sql.SQLException;

@Service
public class AuthServiceJdbcImpl implements AuthService{
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceJdbcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authUser(User user) {

        try {
            User usr = userRepository.findByLogin(user.getLogin());
            return usr.getId() > 0 && usr.getPassword().equals(user.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
