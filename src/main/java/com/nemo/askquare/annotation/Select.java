package com.nemo.askquare.annotation;

import com.nemo.askquare.handler.DefaultResultSetHandler;
import org.apache.commons.dbutils.ResultSetHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {

    String sql();

    Class<? extends DefaultResultSetHandler> resultSetHandlerClass() default DefaultResultSetHandler.class;

}
