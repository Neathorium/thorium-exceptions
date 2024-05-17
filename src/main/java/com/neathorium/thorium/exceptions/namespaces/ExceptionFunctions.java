package com.neathorium.thorium.exceptions.namespaces;

import com.neathorium.thorium.exceptions.constants.ExceptionConstants;
import com.neathorium.thorium.exceptions.records.ExceptionData;

import java.util.Objects;

public interface ExceptionFunctions {
    private static <T extends Exception> boolean isReferenceOrMessageEquals(T exception, T expected) {
        return (
            Objects.equals(expected, exception) ||
            Objects.equals(expected.getMessage(), exception.getMessage())
        );
    }

    static boolean isException(Exception ex) {
        final var exception = ExceptionConstants.EXCEPTION;
        return !(Objects.isNull(ex) || ExceptionFunctions.isReferenceOrMessageEquals(ex, exception));
    }

    static boolean isNonException(Exception ex) {
        final var exception = ExceptionConstants.EXCEPTION;
        return !Objects.isNull(ex) && ExceptionFunctions.isReferenceOrMessageEquals(ex, exception);
    }

    static RuntimeException getRuntimeExcpetion(ExceptionData<RuntimeException> data, Exception exception) {
        return data.CONSTRUCTOR().apply(data.MESSAGE() + exception.getMessage(), exception);
    }
}
