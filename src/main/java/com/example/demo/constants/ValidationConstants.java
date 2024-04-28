package com.example.demo.constants;

public class ValidationConstants {

    public static final String INVALID_EMAIL = "Invalid email address";
    public final static String NAME_REGEXP = "^[A-Z][a-zA-Z]*$";
    public final static String NAME_MESSAGE = "Name must start with an uppercase letter and contain only Latin characters";
    public final static String ADDRESS_REGEXP = "^[a-zA-Z0-9\\s,'-]*$";
    public final static String ADDRESS_MESSAGE = "Invalid address format";
    public final static String PHONE_REGEXP = "^\\d{3}-\\d{3}-\\d{4}$";
    public final static String PHONE_MESSAGE = "Invalid phone number format. It should be in the format XXX-XXX-XXXX";
    public final static String AGE_ERROR_MESSAGE = "User must be at least %s years old.";

}
