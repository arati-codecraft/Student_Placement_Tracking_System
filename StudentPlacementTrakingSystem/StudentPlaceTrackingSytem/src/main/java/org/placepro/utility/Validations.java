package org.placepro.utility;

public class Validations {
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("\\d{10}");
    }
}
