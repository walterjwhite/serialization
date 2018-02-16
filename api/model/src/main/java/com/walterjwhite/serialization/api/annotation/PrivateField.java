package com.walterjwhite.serialization.api.annotation;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface PrivateField {}
