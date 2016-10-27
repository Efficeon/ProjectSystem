package ProjectSystem.dao;

import ProjectSystem.view.ConsoleHelper;
import ProjectSystem.model.Project;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;

public class ProjectDao{
    private List<Project> listProjects = null;

    public List<Project> readingAllElements() throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listProjects = session.createQuery("FROM Project").list();
        return listProjects;
    }

    public List<Project> readingProjectsElements(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listProjects = session.createQuery("FROM Project WHERE projectID="+projectID).list();
        session.close();
        return listProjects;
    }

    public void updateElement(int projectID, String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = (Project) session.get(Project.class, projectID);
        project.setName(name);
        session.update(project);
        session.close();
    }

    public void deleteElement(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = (Project) session.get(Project.class, projectID);
        session.delete(project);
        session.close();
    }

    public void createElement(int projectID, String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = new Project(projectID, name);
        session.saveOrUpdate(project);
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
