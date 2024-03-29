package no.kristiania;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    private final MemberDao memberDao;
    private PGSimpleDataSource dataSource;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private ProjectDao projectDao;

    public Main() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("task-manager.properties"));

        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setUrl(properties.getProperty("dataSource.url"));
        dataSource.setUser(properties.getProperty("dataSource.user"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));

        Flyway.configure().dataSource(dataSource).load().migrate();

        projectDao = new ProjectDao(dataSource);
        memberDao = new MemberDao(dataSource);
    }

    public static void main(String[] args) throws IOException, SQLException {
        new Main().run();
    }

    private void run() throws IOException, SQLException {
        System.out.println("Choose action: create [project]|create [member]");
        String action = input.readLine();

        switch (action.toLowerCase()) {
            case "project":
                insertProject();
                break;
            case "member":
                insertMember();
                break;
        }

        System.out.println("Current projects " + projectDao.listAll());
    }

    private void insertMember() throws IOException, SQLException {
        System.out.println("Please type the name of a new member");
        Member member = new Member();
        member.setName(input.readLine());
        System.out.println("Please type the email of the new member");
        member.setEmail(input.readLine());
        memberDao.insert(member);
    }

    private void insertProject() throws IOException, SQLException {
        System.out.println("Please type the name of a new project");
        String projectName = input.readLine();
        Project project = new Project();
        project.setName(projectName);
        projectDao.insert(project);
    }
}
