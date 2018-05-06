package com.nemo.askquare.handler;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler implements ResultSetHandler<Object> {

    private boolean isCollection;
    private DefaultRowResultSetHandler rowHandler;

    public DefaultResultSetHandler(Class classEntity, boolean isCollection) {
        this.rowHandler = new DefaultRowResultSetHandler(classEntity);
        this.isCollection = isCollection;
    }

    @Override
    public Object handle(ResultSet resultSet) throws SQLException {
        if (isCollection) {
            List<Object> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(rowHandler.handle(resultSet));
            }
            return list;
        } else {
            if (resultSet.next()) {
                return rowHandler.handle(resultSet);
            } else {
                return null;
            }
        }
    }

}
