package ru.xpendence.slimer.config.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.xpendence.slimer.dto.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 13:52
 * e-mail: v.chernyshov@pflb.ru
 */
public class JwtUser implements UserDetails {

    private final Long id;
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final boolean enabled;
    private final LocalDateTime lastPasswordReset;
    private final Collection<? extends GrantedAuthority> authorities;

    private JwtUser(Long id,
                    String login,
                    String firstName,
                    String lastName,
                    String password,
                    boolean enabled,
                    LocalDateTime lastPasswordReset,
                    Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.enabled = enabled;
        this.lastPasswordReset = lastPasswordReset;
        this.authorities = authorities;
    }

    public static JwtUser of(UserDto user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                true,
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> roles) {
        return roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getLastPasswordReset() {
        return lastPasswordReset;
    }
}
