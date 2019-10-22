package no.kristiania;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDao extends AbstractDao<Member> {

    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertObject(Member member, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, member.getName());
    }

    @Override
    protected Member readObject(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setName(rs.getString("name"));
        return member;
    }

    public void insert(Member member) throws SQLException {
        insert(member, "insert into members (name) values (?)");
    }

    public List<Member> listAll() throws SQLException {
        return listAll("select * from members");
    }

}
