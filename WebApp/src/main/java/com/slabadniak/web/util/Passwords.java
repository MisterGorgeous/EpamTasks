package com.slabadniak.web.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Passwords {
    private Passwords(){}

    public static String hashPassword(String password){
        return DigestUtils.md5Hex(password);
    }

    public static String showPassword(String password){
        return password.isEmpty() ? "the same" : password;
    }
}
