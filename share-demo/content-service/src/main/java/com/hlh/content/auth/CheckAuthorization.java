package com.hlh.content.auth;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author hlh
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
