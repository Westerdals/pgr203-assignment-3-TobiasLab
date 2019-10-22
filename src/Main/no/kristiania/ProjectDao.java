package no.kristiania;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectDao extends AbstractDao<Project> {

    public ProjectDao(DataSource dataSource) {
        super(dataSource);
    }



    @Override
    protected void insertObject(Project project, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, project.getName());
    }


    @Override
    protected Project readObject(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        return new Project();

    }

    public void insert(Project project) throws SQLException {
        insert(project, "insert into projects (name) values (?)");
    }

    public List<Project> listAll() throws SQLException {
        return listAll("select * from projects");
    }
}
