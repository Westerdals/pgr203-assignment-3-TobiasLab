package no.kristiania;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public void insert(String project) throws SQLException {
        insert(project, "inserts into projects (name) values (?)");
    }

    public List<String> listAll() throws SQLException {
        return listAll("select * from products");
    }
}
