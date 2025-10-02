package com.vitalx.apizoo.common;

public sealed interface UseCaseResult<GenericSuccessValue, GenericErrorValue>
        permits UseCaseResult.Success, UseCaseResult.Failure {

    static <GenericSuccessValue, GenericErrorValue>
    UseCaseResult<GenericSuccessValue, GenericErrorValue> success(GenericSuccessValue value) {
        return new Success<>(value);
    }

    static <GenericSuccessValue, GenericErrorValue>
    UseCaseResult<GenericSuccessValue, GenericErrorValue> failure(GenericErrorValue error) {
        return new Failure<>(error);
    }

    record Success<GenericSuccessValue, GenericErrorValue>
            (GenericSuccessValue value) implements UseCaseResult<GenericSuccessValue, GenericErrorValue> {
    }

    record Failure<GenericFailureValue, GenericErrorValue>
            (GenericErrorValue error) implements UseCaseResult<GenericFailureValue, GenericErrorValue> {
    }
}
