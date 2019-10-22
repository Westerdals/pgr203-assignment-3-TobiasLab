package no.kristiania;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectDaoTest {

    @Test
    void shouldListInsertedProducts() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myTestDatabase");

        dataSource.getConnection().createStatement().executeUpdate(
                "create table PROJECTS (id serial primary key, name varcahr(1000) not null)"
        );

        ProjectDao  dao = new ProjectDao(dataSource);
        String project = new sampleProject();
        dao.insert(project);
        assertThat(dao.listAll())
                .contains(project);
    }

    private String sampleProject() {
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
