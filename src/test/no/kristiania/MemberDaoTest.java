package no.kristiania;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

public class MemberDaoTest {

    private DataSource dataSource;

    @Test
    void shouldListProjectMember() {
        Member member = new Member();
        MemberDao dao = new MemberDao(dataSource);

        dao.insert(member);
        assertThat(dao.listAll()).contains(order);
    }
}
