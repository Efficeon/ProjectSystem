package ProjectSystem.dao;

import ProjectSystem.model.Developer;
import ProjectSystem.model.Project;
import ProjectSystem.model.Team;
import ProjectSystem.view.ConsoleHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class TeamDao{
    private List<Team> listTeams = null;
    private Set<Developer> developers = null;
    DeveloperDao developerDao = null;

    private static Logger logger = LoggerFactory.getLogger(DeveloperDao.class);

    public void updateElement(int teamID, String name){
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Team team = (Team) session.get(Team.class, teamID);
        team.setName(name);
        transaction.commit();
        logger.info("Update Team: " + team.getName());
        session.close();
    }

    public void addDeveloper(int teamID, int developerID){
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Team team = (Team) session.get(Team.class, teamID);
        developers = team.getDevelopers();
        developerDao = new DeveloperDao();
        developers.add(developerDao.readingDevelopers(developerID));
        team.setDevelopers(developers);
        session.update(team);
        transaction.commit();
        logger.info("Add developer: " + team.getName());
        session.close();
    }

    public void deleteElement(int teamID){
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Team team = (Team) session.get(Team.class, teamID);
        session.delete(team);
        transaction.commit();
        logger.info("Delete Team: " + team.getName());
        session.close();
    }

    public int createElement(String name) {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Team team = new Team(name);
        Integer teamID = (Integer)session.save(team);
        logger.info("Create Team: " + team.getName());
        transaction.commit();
        session.close();
        return teamID;
    }

    public void showAllTeams(){
        Session session = ConnectDao.sessionFactory.openSession();
        listTeams = session.createQuery("FROM Team").list();
        logger.info("Reading all Teams: " + listTeams);
        for (Team team : listTeams){
            ConsoleHelper.writeMessage(team.toString());
        }
        session.close();
    }

    public void showTeam(int teamID){
        Session session = ConnectDao.sessionFactory.openSession();
        Team team = (Team) session.get(Team.class, teamID);
        ConsoleHelper.writeMessage(team.toString());
        session.close();
    }

    public Team readingTeam(int teamID) {
        Session session = ConnectDao.sessionFactory.openSession();
        Team team = (Team) session.get(Team.class, teamID);
        session.close();
        return team;
    }
}
