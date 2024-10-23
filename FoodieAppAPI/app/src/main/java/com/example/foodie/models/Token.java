package com.example.foodie.models;

public class Token {
    private String tokenString;
    private String expiration;

    public Token() {
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenString='" + tokenString + '\'' +
                ", expiration='" + expiration + '\'' +
                '}';
    }
}
