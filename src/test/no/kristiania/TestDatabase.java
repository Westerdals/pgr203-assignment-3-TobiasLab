package no.kristiania;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

public class TestDatabase {
    public static JdbcDataSource testDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myprojectdb;DB_CLOSE_DELAY=-1");

        Flyway.configure().dataSource(dataSource).load().migrate();
        return dataSource;
    }
}
