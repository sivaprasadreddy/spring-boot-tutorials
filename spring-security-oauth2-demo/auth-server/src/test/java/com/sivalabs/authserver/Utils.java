package com.sivalabs.authserver;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("client1secret", "siva","admin");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        strings.forEach( str -> {
            System.out.println("Plain: "+str+", encoded: "+encoder.encode(str));
        });

    }
}
