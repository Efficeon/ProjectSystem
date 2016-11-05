package ProjectSystem.dao;

import ProjectSystem.model.Team;
import ProjectSystem.view.ConsoleHelper;
import ProjectSystem.model.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class ProjectDao{

    private Set<Team> teams = null;
    private TeamDao teamDao = null;
    private List<Project> listProjects = null;
    private static Logger logger = LoggerFactory.getLogger(DeveloperDao.class);

    public int createElement(String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Project project = new Project(name);
        int projectID = (Integer)session.save(project);
        transaction.commit();
        logger.info("Create Project: " + project.getName());
        session.close();
        return projectID;
    }

    public void updateElement(int projectID, String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Project project = (Project) session.get(Project.class, projectID);
        project.setName(name);
        transaction.commit();
        logger.info("Update Project: " + project.getName());
        session.close();
     }

    public void addTeam(int projectID, int teamID){
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Project project = (Project) session.get(Project.class, projectID);
        teams = project.getTeams();
        teamDao = new TeamDao();
        teams.add(teamDao.readingTeam(teamID));
        project.setTeams(teams);
        session.update(project);
        transaction.commit();
        logger.info("Add team: " + project.getName());
        session.close();
    }

    public void deleteElement(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Project project = (Project) session.get(Project.class, projectID);
        session.delete(project);
        transaction.commit();
        logger.info("Update Project: " + project.getName());
        session.close();
    }

    public void showAllProjects() throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listProjects = session.createQuery("FROM Project").list();
        logger.info("Reading all TeamsProjects " + listProjects);
        for (Project project : listProjects){
            ConsoleHelper.writeMessage(project.toString());
        }
        session.close();
    }

    public void showProject(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        session.beginTransaction();
        Project project = (Project) session.load(Project.class, projectID);
        ConsoleHelper.writeMessage(project.toString());
        session.getTransaction().commit();
        session.close();
    }
}
