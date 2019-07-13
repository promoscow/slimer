package ru.xpendence.slimer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 14:34
 * e-mail: v.chernyshov@pflb.ru
 */
@Configuration
public class BeanInitialization {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
