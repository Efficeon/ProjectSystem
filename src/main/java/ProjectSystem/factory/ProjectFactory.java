package ProjectSystem.factory;

import ProjectSystem.dao.ProjectDao;

import java.sql.SQLException;

public class ProjectFactory {
    public void createProject(String name) throws SQLException {
        ProjectDao projectDao = new ProjectDao();
        projectDao.createElement(name);
    }
}
