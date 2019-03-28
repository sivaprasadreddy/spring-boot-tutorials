package com.sivalabs.ebuddy.utils;

import com.sivalabs.ebuddy.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static java.lang.String.format;

public class TestHelper {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<String> strings = Arrays.asList("siva", "admin");
        strings.forEach(it -> {
            System.out.println("Plain:" + it + ", encoded:" + encoder.encode(it));
        });
    }

    public static User buildUser() {
        String uuid = UUID.randomUUID().toString();
        return User.builder()
                .email(format("someone-%s@gmail.com", uuid))
                .password(uuid)
                .name("name-" + uuid)
                .build();
    }

    public static User buildUserWithId() {
        Random random = new Random();
        String uuid = UUID.randomUUID().toString();
        return User.builder()
                .id(random.nextLong())
                .email(format("someone-%s@gmail.com", uuid))
                .password(uuid)
                .name("name-" + uuid)
                .build();
    }
}
