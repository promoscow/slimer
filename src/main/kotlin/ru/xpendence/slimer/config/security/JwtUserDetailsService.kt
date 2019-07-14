package ru.xpendence.slimer.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import ru.xpendence.slimer.config.security.jwt.JwtUser
import ru.xpendence.slimer.service.UserServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 13:19
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Qualifier("jwtUserDetailsService")
class JwtUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userService: UserServiceImpl

    override fun loadUserByUsername(username: String?): UserDetails =
            JwtUser.of(userService.findByLogin(username!!))
}