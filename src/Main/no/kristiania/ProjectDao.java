package no.kristiania;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    protected Project readFromDb(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setName(rs.getString("name"));
        return project;

    }

    public long insert(Project project) throws SQLException {
       return insert(project, "insert into projects (name) values (?)");
    }

    public List<Project> listAll() throws SQLException {
        return listAll("select * from projects");
    }

    public Project retrieve(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("select * from projects where id = ?")) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return readFromDb(rs);
                    } else {
                        return null;
                    }
                }
            }
        }
    }
}
