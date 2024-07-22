package com.koval.shop.util;

import com.koval.shop.annotation.ValidLogo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Component
public class LogoValidator implements ConstraintValidator<ValidLogo, MultipartFile> {

    @Override
    public void initialize(ValidLogo constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) {
            return true;
        }
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!Arrays.asList("gif", "png", "jpg", "jpeg").contains(fileExtension)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            "Invalid file type. Allowed types: gif, png, jpg, jpeg"
                    ).addConstraintViolation();
            return false;
        }
        long fileSizeInBytes = file.getSize();
        long maxSizeInBytes = 10 * 1024 * 1024; // 10MB

        if (fileSizeInBytes > maxSizeInBytes) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            "File size exceeds the maximum allowed limit"
                    ).addConstraintViolation();
            return false;
        }
        return true;
    }
}
