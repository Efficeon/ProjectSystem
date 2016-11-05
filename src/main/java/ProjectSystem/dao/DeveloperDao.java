package ProjectSystem.dao;

import ProjectSystem.model.Team;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ProjectSystem.model.Developer;
import ProjectSystem.view.ConsoleHelper;
import org.hibernate.Session;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperDao{
    private static Logger logger = LoggerFactory.getLogger(DeveloperDao.class);
    private List<Developer> listDevelopers = null;
    private Set<Team> teams = null;

    public int createElement(String name) {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = new Developer(name);
        teams = new HashSet<>();
        developer.setTeams(teams);
        int developerID = (Integer)session.save(developer);
        transaction.commit();
        logger.info("Create Developer: " + developer.getName());
        session.close();
        return developerID;
    }
    public void updateElement(int developerID, String name) {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerID);
        developer.setName(name);
        session.update(developer);
        transaction.commit();
        showDeveloper(developerID);
        logger.info("Update Developer: " + listDevelopers);
        session.close();
    }

    public void deleteElement(int developerID) {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerID);
        session.delete(developer);
        transaction.commit();
        logger.info("Delete Developer: " + developer.getName());
        session.close();
    }

    public void showAllDevelopers() {
        Session session = ConnectDao.sessionFactory.openSession();
        listDevelopers = session.createQuery("FROM Developer").list();
        logger.info("Reading all Developer: " + listDevelopers);
        for (Developer developer :listDevelopers){
            ConsoleHelper.writeMessage(developer.toString());
        }
        session.close();
    }

    public void showDeveloper(int developerID){
        Session session = ConnectDao.sessionFactory.openSession();
        session.beginTransaction();
        Developer developer = null;
        try {
            developer = (Developer) session.load(Developer.class, developerID);
            session.getTransaction().commit();
            ConsoleHelper.writeMessage(developer.toString());
        } catch (ObjectNotFoundException e){
            ConsoleHelper.writeMessage("Разработчик с указанным ID не найден.");
        } finally {
            session.close();
        }
    }

    public Developer readingDevelopers(int developerID) {
        Session session = ConnectDao.sessionFactory.openSession();
        session.beginTransaction();
        Developer developer = (Developer) session.load(Developer.class, developerID);
        session.getTransaction().commit();
        session.close();
        return developer;
    }
}
