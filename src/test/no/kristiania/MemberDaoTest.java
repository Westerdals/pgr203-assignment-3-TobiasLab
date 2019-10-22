package no.kristiania;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberDaoTest {


    @Test
    void shouldFindSavedMembers() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myTestDatabase");

        dataSource.getConnection().createStatement().executeUpdate(
                "create table MEMBERS (id serial primary key, name varchar(1000) not null)"
        );

        Member member = new Member();
        member.setName("Test");
        MemberDao dao = new MemberDao(dataSource);

        dao.insert(member);
        assertThat(dao.listAll()).contains(member);
    }
}
