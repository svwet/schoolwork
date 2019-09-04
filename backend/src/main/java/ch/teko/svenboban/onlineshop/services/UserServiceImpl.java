package ch.teko.svenboban.onlineshop.services;

import ch.teko.svenboban.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author: Petrovic Boban
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findIdByName(username);
    }

    @Override
    public String getMobileFromCurrentUser() {
        return userRepository.getMobileByUserId(getCurrentUserId());
    }

}
