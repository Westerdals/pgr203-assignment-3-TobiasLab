package no.kristiania;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private List<Member> members = new ArrayList<>();

    public MemberDao(DataSource dataSource) {

    }

    public void insert(Member member) {
        members.add(member);
    }

    public List<Member> listAll() {
        return members;
    }
}
