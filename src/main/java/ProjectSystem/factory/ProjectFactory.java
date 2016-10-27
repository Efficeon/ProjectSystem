package ProjectSystem.factory;

import ProjectSystem.dao.ProjectDao;

import java.sql.SQLException;

public class ProjectFactory {
    public void createProject(int projectID, String name) throws SQLException {
        ProjectDao projectDao = new ProjectDao();
        projectDao.createElement(projectID, name);
    }
}
