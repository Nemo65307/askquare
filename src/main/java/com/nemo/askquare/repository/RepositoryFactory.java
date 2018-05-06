package com.nemo.askquare.repository;

import com.nemo.askquare.annotation.*;
import com.nemo.askquare.exception.ApplicationException;
import com.nemo.askquare.handler.DefaultResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.nemo.askquare.repository.ConnectionUtils.getCurrentConnection;

public class RepositoryFactory {

    @SuppressWarnings("unchecked")
    public static <T> T createRepository(Class<T> repositoryInterface) {
        return (T) Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(),
                new Class[]{repositoryInterface}, new RepositoryInvocationHandler());
    }

    private static class RepositoryInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                Connection connection = getCurrentConnection();
                Select select = method.getAnnotation(Select.class);
                if (select != null) {
                    return select(connection, select, method, args);
                }
                Delete delete = method.getAnnotation(Delete.class);
                if (delete != null) {
                    return delete(connection, delete, method, args);
                }
                Insert insert = method.getAnnotation(Insert.class);
                if (insert != null) {
                    return insert(connection, insert, method, args);
                }
                Update update = method.getAnnotation(Update.class);
                if (update != null) {
                    return update(connection, update, method, args);
                }
            } catch (SQLException e) {
                throw new ApplicationException(e);
            }
            throw new UnsupportedOperationException("Operation is not implemented yet: " + method);
        }

        private Object[] getInsertArguments(Object[] args) throws IllegalAccessException {
            if (args.length == 1) {
                Object entity = args[0];
                Field fields[] = entity.getClass().getDeclaredFields();
                List<Object> resolvedArgs = new ArrayList<>();
                for (Field f : fields) {
                    InsertIgnore isIgnored = f.getAnnotation(InsertIgnore.class);
                    if (isIgnored != null || Modifier.isStatic(f.getModifiers())) {
                        continue;
                    } else {
                        f.setAccessible(true);
                        resolvedArgs.add(f.get(entity));
                    }
                }
                return resolvedArgs.toArray();
            } else {
                return args;
            }
        }

        private Object insert(Connection connection, Insert insert, Method method, Object[] args) throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
            Object[] insertArgs = getInsertArguments(args);
            Class<?> returnType = args[0].getClass();
            DefaultResultSetHandler handler = build(returnType, false, insert.resultSetHandlerClass());
            Object entity = new QueryRunner().insert(connection, insert.sql(), handler, insertArgs);
            return entity;
        }

        private Object update(Connection connection, Update update, Method method, Object[] args) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException, SQLException {
            Object[] updateArgs = getInsertArguments(args);
            Object entity = new QueryRunner().update(connection, update.sql(), updateArgs);
            return entity;
        }

        private Object delete(Connection connection, Delete delete, Method method, Object[] args) throws SQLException {
            int result = new QueryRunner().update(connection, delete.sql(), args);
            if (method.getReturnType() == Integer.TYPE) {
                return result;
            } else if (method.getReturnType() == Void.TYPE) {
                return null;
            } else {
                throw new IllegalArgumentException("Method with Delete annotation should return int or void");
            }
        }

        private Object select(Connection connection, Select select, Method method, Object[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
           /* Class<?> returnType = findResultType(method);
            boolean isCollection = Collection.class.isAssignableFrom(method.getReturnType());
            DefaultResultSetHandler handler = build(returnType, isCollection, select.resultSetHandlerClass());
            return new QueryRunner().query(connection, select.sql(),
                    handler, args);*/
            if (method.getReturnType() == Long.class) {
                ResultSetHandler handler = new ScalarHandler();
                Long result = (Long) new QueryRunner().query(connection, select.sql(), handler, args);
                return result;
            }
            Class<?> returnType = findResultType(method);
            boolean isCollection = Collection.class.isAssignableFrom(method.getReturnType());
            DefaultResultSetHandler handler = build(returnType, isCollection, select.resultSetHandlerClass());
            return new QueryRunner().query(connection, select.sql(),
                    handler, args);
        }

        private DefaultResultSetHandler build(Class<?> returnType, boolean isCollection,
                                              Class<? extends DefaultResultSetHandler> resultSetHandlerClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            Constructor<? extends DefaultResultSetHandler> constructor = resultSetHandlerClass.getConstructor(Class.class, Boolean.TYPE);
            return constructor.newInstance(returnType, isCollection);
        }

        private Class<?> findResultType(Method method) {
            ReturnType returnType = method.getAnnotation(ReturnType.class);
            if (returnType != null) {
                return returnType.entityClass();
            } else {
                return method.getReturnType();
            }
        }

    }

}
