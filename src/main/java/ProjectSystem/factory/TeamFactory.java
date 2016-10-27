package ProjectSystem.factory;

import ProjectSystem.dao.TeamDao;

import java.sql.SQLException;

public class TeamFactory {
    public void createTeam(int teamID, String name, int projectID) throws SQLException {
        TeamDao teamDao = new TeamDao();
        teamDao.createElement(teamID, name, projectID);
    }
}
