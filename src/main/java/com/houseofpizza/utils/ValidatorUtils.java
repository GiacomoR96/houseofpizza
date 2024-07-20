package com.houseofpizza.utils;

import jakarta.validation.ConstraintValidatorContext;

public final class ValidatorUtils {

    private ValidatorUtils() {
        throw new IllegalStateException(ValidatorUtils.class.getSimpleName());
    }

    /**
     * Add constraintValidation with fieldName and translated message.
     *
     * @param ctx       context of request input
     * @param errorEnum errorCode ENUM class
     * @param fieldName error field name !!must be equal to DTO property name or will throw 500 error!!
     *                  can be null for generic errors
     */
    public static void addConstraintViolation(ConstraintValidatorContext ctx, Enum<?> errorEnum, String fieldName) {
        String message = "{" + errorEnum.name() + "}";
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(message)
            .addPropertyNode(fieldName)
            .addConstraintViolation();
    }

}

