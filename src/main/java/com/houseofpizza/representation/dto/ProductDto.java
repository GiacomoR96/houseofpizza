package com.houseofpizza.representation.dto;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static com.houseofpizza.exceptions.ValidationCodes.VALIDATION_001;
import static com.houseofpizza.utils.ValidatorUtils.addConstraintViolation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import lombok.Data;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

@Data
@ProductDto.Validation
public class ProductDto {

    @NotNull
    private Long id;

    @NotNull
    private Long quantity;

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Constraint(validatedBy = ProductDto.DataValidator.class)
    @Documented
    public @interface Validation {
        String message() default "";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    public static class DataValidator
        implements ConstraintValidator<ProductDto.Validation, ProductDto> {

        @Override
        public void initialize(ProductDto.Validation constraintAnnotation) {
            //Do nothing
        }

        @Override
        public boolean isValid(ProductDto dto, ConstraintValidatorContext context) {

            if (dto.quantity <= 0L) {
                addConstraintViolation(context, VALIDATION_001, null);
                return false;
            }

            return true;
        }

    }

}

