package ProjectSystem.factory;

import ProjectSystem.dao.DeveloperDao;

import java.sql.SQLException;

public class DeveloperFactory{

    public void createDeveloper(int developerID, String name, int teamID) throws SQLException {
        DeveloperDao developerDao = new DeveloperDao();
        developerDao.createElement(developerID, name, teamID);
    }
}
