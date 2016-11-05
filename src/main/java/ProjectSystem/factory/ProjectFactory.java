package ProjectSystem.factory;

import ProjectSystem.dao.ProjectDao;

import java.sql.SQLException;

public class ProjectFactory {
    public int createProject(String name) throws SQLException {
        ProjectDao projectDao = new ProjectDao();
        int projectID = projectDao.createElement(name);
        return projectID;
    }
}
