package ProjectSystem.dao;

import ProjectSystem.view.ConsoleHelper;
import ProjectSystem.model.Project;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ProjectDao{
    private List<Project> listProjects = null;
    private static Logger logger = LoggerFactory.getLogger(DeveloperDao.class);

    public List<Project> readingAllElements() throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listProjects = session.createQuery("FROM Project").list();
        logger.info("Reading Projects: " + listProjects);
        return listProjects;
    }

    public List<Project> readingProjectsElements(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listProjects = session.createQuery("FROM Project WHERE projectID="+projectID).list();
        logger.info("Reading Project: " + listProjects);
        session.close();
        return listProjects;
    }

    public void updateElement(int projectID, String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = (Project) session.get(Project.class, projectID);
        project.setName(name);
        session.update(project);
        logger.info("Update Project: " + project);
        session.close();
    }

    public void deleteElement(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = (Project) session.get(Project.class, projectID);
        session.delete(project);
        logger.info("Update Project: " + project);
        session.close();
    }

    public void createElement(int projectID, String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = new Project(projectID, name);
        session.saveOrUpdate(project);
        logger.info("Create Project: " + project);
        session.close();
    }

    public void showAllProjects() throws SQLException {
        readingAllElements();
        for (Project project : listProjects){
            ConsoleHelper.writeMessage(project.toString());
        }
    }

    public void showProject(int projectID) throws SQLException {
        readingProjectsElements(projectID);
        for (Project project : listProjects){
            ConsoleHelper.writeMessage(project.toString());
        }
    }
}
