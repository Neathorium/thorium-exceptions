package com.neathorium.thorium.exceptions.records;

import java.util.function.BiFunction;

public record ExceptionData<ExceptionType>(BiFunction<String, Throwable, ExceptionType> CONSTRUCTOR, String MESSAGE) {
}
