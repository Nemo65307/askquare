package com.nemo.askquare.service.impl;

import com.nemo.askquare.annotation.Transactional;
import com.nemo.askquare.exception.ApplicationException;
import com.nemo.askquare.repository.ConnectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class ServiceFactory {

    public static Object createService(DataSource dataSource, Object realService) {
        return Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), realService.getClass().getInterfaces(),
                new ServiceInvocationHandler(dataSource, realService));
    }

    private static class ServiceInvocationHandler implements InvocationHandler {

        private final Object realService;
        private final DataSource dataSource;

        public ServiceInvocationHandler(DataSource dataSource, Object realService) {
            this.realService = realService;
            this.dataSource = dataSource;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method m = findMethod(method);
            Transactional transactional = m.getAnnotation(Transactional.class);
            if (transactional != null) {
                return invokeTransactional(transactional, m, args);
            } else {
                return m.invoke(realService, args);
            }
        }

        private Object invokeTransactional(Transactional transactional, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            try (Connection connection = dataSource.getConnection()) {
                connection.setAutoCommit(false);
                ConnectionUtils.setCurrentConnection(connection);
                if (transactional.readOnly()) {
                    return method.invoke(realService, args);
                } else {
                    try {
                        Object result = method.invoke(realService, args);
                        connection.commit();
                        return result;
                    } catch (Exception e) {
                        if (e instanceof RuntimeException) {
                            connection.rollback();
                        } else {
                            connection.commit();
                        }
                        throw e;
                    }
                }
            } catch (SQLException e) {
                throw new ApplicationException(e);
            } finally {
                ConnectionUtils.removeCurrentConnection();
            }
        }

        private Method findMethod(Method method) {
            for (Method m : realService.getClass().getDeclaredMethods()) {
                if (m.getName().equals(method.getName()) &&
                        Arrays.equals(m.getParameterTypes(), method.getParameterTypes())) {
                    return m;
                }
            }
            throw new IllegalArgumentException("Can't find method " + method + " in the " + realService.getClass());
        }


    }

}
