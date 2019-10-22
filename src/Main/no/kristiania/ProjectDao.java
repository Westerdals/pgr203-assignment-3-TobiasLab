package no.kristiania;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    private DataSource dataSource;

    public ProjectDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(String project) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into projects (name) values (?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, project);
                stmt.executeUpdate();
            }
        }
    }

    public List<String> listAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("select * from projects")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    List<String> projects = new ArrayList<>();
                    while (rs.next()) {
                        projects.add(rs.getString("name"));
                    }
                    return projects;
                }
            }
        }
    }
}
