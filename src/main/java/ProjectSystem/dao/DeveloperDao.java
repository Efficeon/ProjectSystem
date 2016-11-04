package ProjectSystem.dao;

import ProjectSystem.model.Team;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ProjectSystem.model.Developer;
import ProjectSystem.view.ConsoleHelper;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperDao{
    private List<Developer> listDevelopers = null;
    private static Logger logger = LoggerFactory.getLogger(DeveloperDao.class);
    private TeamDao teamDao = null;
    private Set<Team> teams = null;

    public void updateElement(int developerID, int teamID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerID);
        developer.setTeamID(teamID);
        session.update(developer);
        transaction.commit();
        showDeveloper(developerID);
        logger.info("update Developer: " + listDevelopers);
        session.close();
    }

    public void deleteElement(int developerID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerID);
        session.delete(developer);
        transaction.commit();
        logger.info("Delete Developer: " + developer);
        session.close();
    }

    public void createElement(String name, int teamID) throws SQLException {
        Team team = teamDao.readingTeam(teamID);
        teams.add(team);

        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = new Developer(name, teamID);

        teams = new HashSet<>();
        developer.setTeams(teams);
        session.save(developer);
        transaction.commit();
        logger.info("Create Developer: " + developer);
        session.close();
        showDeveloper(developer.getDeveloperID());
    }

    public void showAllDevelopers() throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listDevelopers = session.createQuery("FROM Developer").list();
        logger.info("Reading all Developer: " + listDevelopers);
        for (Developer developer :listDevelopers){
            ConsoleHelper.writeMessage(developer.toString());
        }
        session.close();
    }

    public void showDeveloper(int developerID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Developer developer = (Developer) session.get(Developer.class, developerID);
        ConsoleHelper.writeMessage(developer.toString());
        session.close();
    }
}
