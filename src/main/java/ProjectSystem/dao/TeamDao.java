package ProjectSystem.dao;

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
    private Set<Project> projects = null;
    ProjectDao projectDao = null;
    private static Logger logger = LoggerFactory.getLogger(DeveloperDao.class);

    public void updateElement(int teamID, String name, int projectID){
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Team team = (Team) session.get(Team.class, teamID);
        team.setName(name);

        projects = team.getProjects();
        projectDao = new ProjectDao();
        projects.add(projectDao.readingProject(projectID));
        team.setProjects(projects);
        session.update(team);
        session.merge(team);
        transaction.commit();
        showTeam(teamID);
        logger.info("Update Team: " + team);
        session.close();
        showTeam(teamID);
    }

    public void deleteElement(int teamID){
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Team team = (Team) session.get(Team.class, teamID);
        session.delete(team);
        transaction.commit();
        logger.info("Delete Team: " + team);
        session.close();
    }

    public void createElement(String name) {
        Session session = ConnectDao.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Team team = new Team(name);
        session.save(team);
        logger.info("Create Team: " + team);
        transaction.commit();
        session.close();
        showTeam(team.getTeamID());
    }

    public void showAllTeams(){
        Session session = ConnectDao.sessionFactory.openSession();
        listTeams = session.createQuery("FROM Team ").list();
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
