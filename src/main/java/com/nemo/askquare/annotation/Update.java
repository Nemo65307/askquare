package com.nemo.askquare.annotation;

import com.nemo.askquare.handler.DefaultResultSetHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Update {

    String sql();

    Class<? extends DefaultResultSetHandler> resultSetHandlerClass() default DefaultResultSetHandler.class;

}
