package ProjectSystem.factory;

import ProjectSystem.dao.TeamDao;

import java.sql.SQLException;

public class TeamFactory {
    public int createTeam(String name) throws SQLException {
        TeamDao teamDao = new TeamDao();
        int teamID = teamDao.createElement(name);
        return teamID;
    }
}
