package ru.xpendence.slimer.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.xpendence.slimer.entity.User;
import ru.xpendence.slimer.service.UserServiceImpl;

import java.util.Optional;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 13:05
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
public class JwtUserDetailsServiceOld implements UserDetailsService {

    private final UserServiceImpl userService;

    public JwtUserDetailsServiceOld(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userService.findByLogin(username))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found by username: %s", username)));
        return null;
    }
}
