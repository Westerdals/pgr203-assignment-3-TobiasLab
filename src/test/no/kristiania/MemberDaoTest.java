package no.kristiania;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberDaoTest {

    private DataSource dataSource;

    @Test
    void shouldFindSavedMembers() throws SQLException {
        Member member = new Member();
        member.setName("Test");
        MemberDao dao = new MemberDao(dataSource);

        dao.insert(member);
        assertThat(dao.listAll()).contains(member);
    }
}
