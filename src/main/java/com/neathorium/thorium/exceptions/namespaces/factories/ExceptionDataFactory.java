package com.neathorium.thorium.exceptions.namespaces.factories;

import com.neathorium.thorium.exceptions.records.ExceptionData;

import java.util.function.BiFunction;

public interface ExceptionDataFactory {
    private static <ExceptionType> ExceptionData<ExceptionType> getWithCore(
        BiFunction<BiFunction<String, Throwable, ExceptionType>, String, ExceptionData<ExceptionType>> constructor,
        BiFunction<String, Throwable, ExceptionType> exceptionConstructor,
        String message
    ) {
        return constructor.apply(exceptionConstructor, message);
    }

    static <ExceptionType> ExceptionData<ExceptionType> getWith(BiFunction<String, Throwable, ExceptionType> exceptionConstructor, String message) {
        return getWithCore(ExceptionData::new, exceptionConstructor, message);
    }
}
