package no.kristiania;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberDaoTest {

    private JdbcDataSource dataSource = TestDatabase.testDataSource();

    @Test
    void shouldFindSavedMembers() throws SQLException {
        Member member = new Member();
        member.setName("Test");
        MemberDao dao = new MemberDao(dataSource);

        dao.insert(member);
        assertThat(dao.listAll()).contains(member);
    }
}
