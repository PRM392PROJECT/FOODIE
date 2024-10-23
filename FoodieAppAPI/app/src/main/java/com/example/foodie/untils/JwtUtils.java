package com.example.foodie.untils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class JwtUtils {
    public static String decodeJwt(String jwtEncoded) throws Exception {
        String[] split = jwtEncoded.split("\\.");
        if (split.length == 3) {
            return getJson(split[1]);
        } else {
            throw new Exception("Invalid token format");
        }
    }

    // Giải mã Base64Url
    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
