package com.codegym.springuploadfile.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class BadWordLogAspect {
    @Before("execution(* com.codegym.springuploadfile.controller.ProductController.saveProduct(..)) && args(productForm)")
    public void logBadWord(JoinPoint joinPoint, Object productForm) {
        try {
            Class<?> clazz = productForm.getClass();
            String feedback = (String) clazz.getMethod("getFeedback").invoke(productForm);
            String author = (String) clazz.getMethod("getAuthor").invoke(productForm);
            if (feedback != null && containsBadWord(feedback)) {
                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                System.out.println("[BAD WORD LOG] Author: " + author + ", Feedback: " + feedback + ", Time: " + time);
            }
        } catch (Exception e) {
            // Ignore reflection errors
        }
    }

    private boolean containsBadWord(String text) {
        String[] badWords = {"xau", "tuc", "che", "ngo", "batlichsu"};
        String lowerText = text.toLowerCase();
        return Arrays.stream(badWords).anyMatch(lowerText::contains);
    }
}

