package com.sivalabs.todolist.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String[] secrets = { "admin", "siva"};
        for (String secret : secrets) {
            System.out.println(secret+":"+encoder.encode(secret));
        }
    }
}
