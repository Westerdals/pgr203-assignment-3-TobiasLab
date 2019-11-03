package no.kristiania;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberDaoTest {
    private JdbcDataSource dataSource;
    private MemberDao dao;

    //private JdbcDataSource dataSource = TestDatabase.testDataSource();

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myTestDatabase");
        dataSource.getConnection().createStatement().executeUpdate(
                "create table if not exists MEMBERS (" +
                        "id serial primary key, name varchar(100) not null," +
                        " email varchar(100) not null" + ")"
        );
        dao = new MemberDao(dataSource);
    }

    @Test
    void shouldFindSavedMembers() throws SQLException {
        Member member = new Member();
        member.setName("Test");
        member.setEmail("Test");
        MemberDao dao = new MemberDao(dataSource);

        dao.insert(member);
        assertThat(dao.listAll()).contains(member);
    }
}
