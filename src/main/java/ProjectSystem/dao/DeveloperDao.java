package ProjectSystem.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ProjectSystem.model.Developer;
import ProjectSystem.view.ConsoleHelper;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;

public class DeveloperDao{
    private List<Developer> listDevelopers = null;
    private static Logger logger = LoggerFactory.getLogger(DeveloperDao.class);

    protected List<Developer> readingAllElements() throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listDevelopers = session.createQuery("FROM Developer").list();
        logger.info("Reading all Developer: " + listDevelopers);
        session.close();
        return listDevelopers;
    }

    /*protected List<Developer> readingTeamsElements(int teamID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listDevelopers = session.createQuery("FROM Developer WHERE teamID="+teamID).list();
        session.close();
        return listDevelopers;
    }*/

    protected List<Developer> readingElement(int developerID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listDevelopers = session.createQuery("FROM Developer WHERE teamID="+developerID).list();
        logger.info("Reading Developer: " + listDevelopers);
        session.close();
        return listDevelopers;
    }

    public void updateElement(int developerID, int teamID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Developer developer = (Developer) session.get(Developer.class, developerID);
        developer.setTeamID(teamID);
        session.update(developer);
        logger.info("update Developer: " + listDevelopers);
        session.close();
    }

    public void deleteElement(int developerID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Developer developer = (Developer) session.get(Developer.class, developerID);
        session.delete(developer);
        logger.info("delete Developer: " + developer);
        session.close();
    }

    public void createElement(int developerID, String name, int teamID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Developer developer = new Developer(developerID, name, teamID);
        session.save(developer);
        logger.info("create Developer: " + developer);
        session.close();
    }

    public void showAllDevelopers() throws SQLException {
        readingAllElements();
        for (Developer developer :listDevelopers){
            ConsoleHelper.writeMessage(developer.toString());
        }
    }

    public void showDeveloper(int developerID) throws SQLException {
        readingElement(developerID);
        for (Developer developer :listDevelopers){
            ConsoleHelper.writeMessage(developer.toString());
        }
    }
}
