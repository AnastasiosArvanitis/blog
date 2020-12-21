package info.anastasios.blog.servlets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_USER_NAME_REGEX =
            Pattern.compile("(?i)(^[a-z]){0,25}", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String userEmail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userEmail);
        return matcher.find();
    }

    public static boolean validateName(String userName) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userName);
        return matcher.find();
    }

}
