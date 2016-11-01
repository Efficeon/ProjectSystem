package ProjectSystem.dao;

import ProjectSystem.model.Team;
import ProjectSystem.view.ConsoleHelper;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class TeamDao{
    private List<Team> listTeams = null;
    private static Logger logger = LoggerFactory.getLogger(DeveloperDao.class);

    public List<Team> readingAllElements() throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listTeams= session.createQuery("FROM Team").list();
        logger.info("Reading all Teams: " + listTeams);
        session.close();
        return listTeams;
    }

    public List<Team> readingTeamsElements(int teamID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listTeams = session.createQuery("FROM Team WHERE teamID="+teamID).list();
        logger.info("Reading Team: " + listTeams);
        session.close();
        return listTeams;
    }

    public List<Team> readingProjectsElements(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listTeams = session.createQuery("FROM Team WHERE projectID="+projectID).list();
        logger.info("Reading projects elements: " + listTeams);
        session.close();
        return listTeams;
    }

    public void updateElement(int teamID, String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Team team = (Team) session.get(Team.class, teamID);
        team.setName(name);
        session.update(team);
        logger.info("Update Team: " + team);
        session.close();
    }

    public void deleteElement(int teamID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Team team = (Team) session.get(Team.class, teamID);
        session.delete(team);
        logger.info("Delete Team: " + team);
        session.close();
    }

    public void createElement(int teamID, String name, int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Team team = new Team(teamID, name, projectID);
        session.saveOrUpdate(team);
        logger.info("Create Team: " + team);
        session.close();
    }

    public void showAllTeams() throws SQLException {
        readingAllElements();
        for (Team team : listTeams){
            ConsoleHelper.writeMessage(team.toString());
        }
    }

    public void showTeam(int teamID) throws SQLException {
        readingTeamsElements(teamID);
        for (Team team : listTeams){
            ConsoleHelper.writeMessage(team.toString());
        }
    }
}
