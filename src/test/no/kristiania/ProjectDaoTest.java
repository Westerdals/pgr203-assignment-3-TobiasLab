package no.kristiania;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectDaoTest {

    @Test
    void shouldListInsertedProducts() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myTestDatabase;DB_CLOSE_DELAY=-1");

        Flyway.configure().dataSource(dataSource).load().migrate();

        ProjectDao  dao = new ProjectDao(dataSource);
        Project project = sampleProject();
        dao.insert(project);
        assertThat(dao.listAll())
                .contains(project);
    }

    private Project sampleProject() {
        Project project = new Project();
        project.setName(sampleProjectName());
        return project;
    }

    private String sampleProjectName() {
        String[] alternatives = {
                "Building Project", "Carpentry Project", "Painting Project", "Electrician Project"
        };

        return alternatives[new Random().nextInt(alternatives.length)];
    }
}
