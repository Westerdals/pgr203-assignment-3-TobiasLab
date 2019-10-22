package no.kristiania;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectDaoTest {

    private JdbcDataSource dataSource;
    private ProjectDao dao;

    //JdbcDataSource dataSource = TestDatabase.testDataSource();

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myTestDatabase");
        dataSource.getConnection().createStatement().executeUpdate(
                "create table if not exists PROJECTS (" +
                        "id serial primary key, name varchar(100) not null" + ")"
        );
        dao = new ProjectDao(dataSource);
    }

    @Test
    void shouldListInsertedProjects() throws SQLException {
        ProjectDao  dao = new ProjectDao(dataSource);
        Project project = sampleProject();
        dao.insert(project);
        assertThat(dao.listAll())
                .contains(project);
    }

    @Test
    public void shouldSaveAllProjects() throws SQLException {
        ProjectDao dao = new ProjectDao(dataSource);
        Project project = sampleProject();
        long id = dao.insert(project);
        assertThat(dao.retrieve(id))
                .isEqualToComparingFieldByField(project);
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
