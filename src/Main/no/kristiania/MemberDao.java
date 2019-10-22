package no.kristiania;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends AbstractDao<Member> {
    private List<Member> members = new ArrayList<>();

    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public void insert(Member member, String sql1) {
        members.add(member);
    }

    @Override
    protected void insertObject(Member project, PreparedStatement stmt) throws SQLException {

    }

    public List<Member> listAll(String sql) {
        return members;
    }

    @Override
    protected Member readObject(ResultSet rs) throws SQLException {
        return null;
    }

    public void insert(Member project) throws SQLException {
        insert(project, "inserts into projects (name) values (?)");
    }

    public List<Member> listAll() throws SQLException {
        return listAll("select * from products");
    }
}
