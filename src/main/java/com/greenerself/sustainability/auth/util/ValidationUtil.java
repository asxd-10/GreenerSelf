package com.greenerself.sustainability.auth.util;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;

@Component
public class ValidationUtil {
    // Email validation pattern (regex for email format)
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Contact number validation pattern assuming a 10-digit number)
    private static final String CONTACT_NUMBER_REGEX = "^[0-9]{10}$"; //TODO Strict check for each International Code
    private static final Pattern CONTACT_NUMBER_PATTERN = Pattern.compile(CONTACT_NUMBER_REGEX);

    // Password validation pattern - Minimum eight and maximum 25 characters, at least one uppercase letter, one lowercase letter, one number and one special character
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,25}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    // Allowed roles (example roles, can be modified as needed)
    private static final Set<String> ALLOWED_ROLES = Set.of("USER", "ADMIN");

    // Method to validate email format
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Method to validate contact number format
    public static boolean isValidContactNumber(String contactNumber) {
        if (contactNumber == null) {
            return false;
        }
        return CONTACT_NUMBER_PATTERN.matcher(contactNumber).matches();
    }

    // Method to validate role
    public static boolean isValidRole(String role) {
        if (role == null) {
            return false;
        }
        return ALLOWED_ROLES.contains(role.toUpperCase());
    }

    // Method to validate password strength
    public static boolean isValidPassword(String password, String username) {
        if (password == null || password.contains("password") || password.contains(username)) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
