package org.swish.svc.database;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple H2 embedded database
 */
@Component
public final class H2DataAccessObject {

    private static final AtomicLong id = new AtomicLong();

    @Autowired
    private H2DataSource h2DataSource;

    public void insertIntoDatabase(String firstName, String surName) throws SQLException {

        String insertStatement = "INSERT INTO PERSON(ID, FIRSTNAME, SURNAME) VALUES(?, ?, ?)";

        PreparedStatement preparedStatement = h2DataSource.getConnection().prepareStatement(insertStatement);
        preparedStatement.setInt(1, getId());
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, surName);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateRecordInDatabase(int id, String firstName, String surName) throws SQLException{

        String updateStatement = "UPDATE PERSON SET SURNAME = ?, FIRSTNAME = ? WHERE ID = ?";

        PreparedStatement preparedStatement = h2DataSource.getConnection().prepareStatement(updateStatement);
        preparedStatement.setString(1, surName);
        preparedStatement.setString(2, firstName);
        preparedStatement.setInt(3, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteRecordFromDatabase(int id) throws SQLException{

        String updateStatement = "DELETE FROM PERSON WHERE ID = ?";

        PreparedStatement preparedStatement = h2DataSource.getConnection().prepareStatement(updateStatement);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    public String getResults() throws SQLException{

        Map<String,String> result = new HashMap<>();
        String queryStatement = "SELECT * FROM PERSON";
        PreparedStatement preparedStatement = h2DataSource.getConnection().prepareStatement(queryStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            result.put(rs.getString("ID"), rs.getString("SURNAME"));
        }
                rs.close();
        preparedStatement.close();
        return new Gson().toJson(result);

    }

    //todo Set sql schema to increment id
    private int getId(){
        return (int)id.incrementAndGet();
    }
}
