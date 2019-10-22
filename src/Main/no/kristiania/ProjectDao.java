package no.kristiania;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDao extends AbstractDao<String> {

    public ProjectDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertObject(String project, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, project);
    }

    @Override
    protected String readObject(ResultSet rs) throws SQLException {
        return rs.getString("name");
    }
}
