package com.slabadniak.web.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * This utils class is used to hash password.
 */
public class Passwords {
    private Passwords(){}

    public static String hashPassword(String password){
        return DigestUtils.md5Hex(password);
    }

}
