package com.neathorium.thorium.exceptions.constants;

public abstract class ExceptionConstants {
    public static final String NON_EXCEPTION_MESSAGE = "No exception occurred.\n";
    public static final Exception EXCEPTION = new Exception(NON_EXCEPTION_MESSAGE);
    public static final RuntimeException RUNTIME_EXCEPTION = new RuntimeException(NON_EXCEPTION_MESSAGE);
}
