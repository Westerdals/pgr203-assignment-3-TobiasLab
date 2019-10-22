package no.kristiania;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberDaoTest {

    private DataSource dataSource;

    @Test
    void shouldFindSavedMembers() {
        Member member = new Member();
        MemberDao dao = new MemberDao(dataSource);

        dao.insert(member);
        assertThat(dao.listAll()).contains(member);
    }
}
