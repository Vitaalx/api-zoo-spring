package com.vitalx.apizoo.common;

public interface AbstractUseCase<
        GenericUseCaseInput,
        GenericSuccessValue,
        GenericErrorValue
        > {
    UseCaseResult<GenericSuccessValue, GenericErrorValue> execute(GenericUseCaseInput input);
}
