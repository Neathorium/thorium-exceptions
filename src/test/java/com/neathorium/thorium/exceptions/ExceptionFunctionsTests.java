package com.neathorium.thorium.exceptions;

import com.neathorium.thorium.exceptions.constants.ExceptionConstants;
import com.neathorium.thorium.exceptions.namespaces.ExceptionFunctions;
import com.neathorium.thorium.exceptions.records.ExceptionData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class ExceptionFunctionsTests {
    public static Stream<Arguments> isExceptionProvider() {
        final Predicate<Exception> function = ExceptionFunctions::isException;
        final var falseNegativeMessage = "Exception was exception.\n";
        final var trueNegativeMessage = "Exception wasn't exception.\n";
        return Stream.of(
            Arguments.of("Exception from Exception Constants isn't an exception(Default Exception object) - aka everything is fine", function, ExceptionConstants.EXCEPTION, false, falseNegativeMessage),
            Arguments.of("Runtime Exception with default exception message is still not an exception", function, new RuntimeException(ExceptionConstants.NON_EXCEPTION_MESSAGE), false, falseNegativeMessage),
            Arguments.of("An exception is an exception", function, new Exception("This is an exception"), true, trueNegativeMessage)
        );
    }

    public static Stream<Arguments> isNonExceptionProvider() {
        final Predicate<Exception> function = ExceptionFunctions::isNonException;
        final var falseNegativeMessage = "Exception was exception.\n";
        final var trueNegativeMessage = "Exception wasn't exception.\n";
        return Stream.of(
            Arguments.of("Exception from Exception Constants isn't an exception(Default Exception object) - aka everything is fine", function, ExceptionConstants.EXCEPTION, true, falseNegativeMessage),
            Arguments.of("Runtime Exception with default exception message is still not an exception", function, new RuntimeException(ExceptionConstants.NON_EXCEPTION_MESSAGE), true, falseNegativeMessage),
            Arguments.of("An exception is an exception", function, new Exception("This is an exception"), false, trueNegativeMessage)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isExceptionProvider")
    public void isExceptionTest(String name, Predicate<Exception> test, Exception exception, boolean status, String errorMessage) {
        final var result = test.test(exception);
        Assertions.assertEquals(result, status, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isNonExceptionProvider")
    public void isNonExceptionTest(String name, Predicate<Exception> test, Exception exception, boolean status, String errorMessage) {
        final var result = test.test(exception);
        Assertions.assertEquals(result, status, errorMessage);
    }


    @Test
    public void getRuntimeExceptionFromRecord() {
        final var exceptionData = new ExceptionData<>(RuntimeException::new, "X");
        final var result = ExceptionFunctions.getRuntimeExcpetion(exceptionData, ExceptionConstants.EXCEPTION);
        Assertions.assertEquals(result.getMessage(), "XNo exception occurred.\n", "Exception message wasn't as expected.\n");
    }
}
