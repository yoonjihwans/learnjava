package xyz.itwill.util;

import java.util.UUID;

public class NewPasswordApp {
    public static String getPasswordOne() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }

    public static void main(String[] args) {
        String pw = getPasswordOne();
        System.out.println("Generated Password: " + pw);
    }
}