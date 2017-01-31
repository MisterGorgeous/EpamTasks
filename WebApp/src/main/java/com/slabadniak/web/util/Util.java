package com.slabadniak.web.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
    private Util(){}

    public static String hashPassword(String password){
        return DigestUtils.md5Hex(password);
    }
}
