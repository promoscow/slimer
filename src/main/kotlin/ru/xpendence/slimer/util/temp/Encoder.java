package ru.xpendence.slimer.util.temp;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 11:12
 * e-mail: v.chernyshov@pflb.ru
 */
public class Encoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("102983469"));

    }
}
