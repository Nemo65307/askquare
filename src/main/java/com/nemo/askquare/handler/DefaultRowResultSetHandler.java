package com.nemo.askquare.handler;

import com.nemo.askquare.annotation.Column;
import com.nemo.askquare.annotation.SelectIgnore;
import org.apache.commons.dbutils.ResultSetHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;

//one row handler
//made package private because doesn't provide the full logic. Part of DefaultResultSetHandler's logic
class DefaultRowResultSetHandler<T> implements ResultSetHandler<T> {

    private final Class<T> classEntity;

    DefaultRowResultSetHandler(Class<T> classEntity) {
        this.classEntity = classEntity;
    }

    public T handle(ResultSet resultSet) throws SQLException {
        try {
            T entity = classEntity.newInstance();
            Field[] fields = classEntity.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) || (field.getAnnotation(SelectIgnore.class) != null)) {
                    continue;
                }
                field.setAccessible(true);
                String columnLabel = field.getName();
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation != null) {
                    columnLabel = columnAnnotation.value();
                }
                Object value = resultSet.getObject(columnLabel);
                field.set(entity, value);
            }
            return entity;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
