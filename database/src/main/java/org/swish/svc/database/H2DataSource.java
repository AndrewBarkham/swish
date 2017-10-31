package org.swish.svc.database;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by andrew on 08/05/17.
 */
@Repository
public class H2DataSource {

    private EmbeddedDatabase embeddedDatabase;

    public Connection getConnection() throws SQLException{
        if(embeddedDatabase == null) {
            embeddedDatabase = createDatabase();
        }
        return embeddedDatabase.getConnection();
    }

    private EmbeddedDatabase createDatabase(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/schema.sql") //location src/main/resources/sql/schema.sql
                .build();
    }

    public void shutdownDatabase(){
        embeddedDatabase.shutdown();
    }
}
