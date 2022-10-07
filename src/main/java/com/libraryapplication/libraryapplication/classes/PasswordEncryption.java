package com.libraryapplication.libraryapplication.classes;

public class PasswordEncryption {
    private int key;

    public PasswordEncryption(int key) {
        this.key = key;
    }

    public String encryptPassword(String password) {
        String encryptedPassword = "";
        char[] chars = password.toCharArray();

        for (char c : chars) {
            c += this.key;
            encryptedPassword += c;
        }

        return encryptedPassword;
    }

    public String decryptPassword(String password) {
        String decryptedPassword = "";
        char[] chars = password.toCharArray();

        for (char c : chars) {
            c -= this.key;
            decryptedPassword += c;
        }

        return decryptedPassword;
    }
}
