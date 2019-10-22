package no.kristiania;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao {
    protected DataSource dataSource;

    public AbstractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(String project) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into projects (name) values (?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                insertObject(project, stmt);
                stmt.executeUpdate();
            }
        }
    }

    protected abstract void insertObject(String project, PreparedStatement stmt) throws SQLException;

    public List<String> listAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("select * from projects")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    List<String> projects = new ArrayList<>();
                    while (rs.next()) {
                        projects.add(readObject(rs));
                    }
                    return projects;
                }
            }
        }
    }

    protected abstract String readObject(ResultSet rs) throws SQLException;
}
