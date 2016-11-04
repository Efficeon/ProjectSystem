package ProjectSystem.factory;

import ProjectSystem.dao.TeamDao;

import java.sql.SQLException;

public class TeamFactory {
    public void createTeam(String name) throws SQLException {
        TeamDao teamDao = new TeamDao();
        teamDao.createElement(name);
    }
}
