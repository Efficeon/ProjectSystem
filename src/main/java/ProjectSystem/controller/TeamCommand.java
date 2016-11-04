package ProjectSystem.controller;

import ProjectSystem.dao.TeamDao;
import ProjectSystem.factory.TeamFactory;
import ProjectSystem.view.ConsoleHelper;

import java.io.IOException;
import java.sql.SQLException;

public class TeamCommand implements Command{

    @Override
    public void execute() throws IOException, ClassNotFoundException, SQLException {
        TeamFactory teamFactory = new TeamFactory();
        TeamDao teamDao = new TeamDao();

        String name;
        int teamID;
        int projectID;

        ConsoleHelper.writeMessage("* * * РАБОЧИЕ ГРУППЫ РАЗРАБОТЧИКОВ * * *" + "\n" +
                "1 - Добавить | 2 - Удалить | 3 - Изменить | 4 - Показать все | 5 - Найти по ID\n");
    int commandNumber = ConsoleHelper.readInt();

        switch (commandNumber){
        case 1:
            ConsoleHelper.writeMessage("Укажите название рабочей группы:\n");
            name = ConsoleHelper.readString();
            teamFactory.createTeam(name);
            ConsoleHelper.writeMessage("\nРабочая группа создана создан!\n");
            break;
        case 2:
            ConsoleHelper.writeMessage("Укажите ID рабочей группы:\n");
            teamID = ConsoleHelper.readInt();
            teamDao.deleteElement(teamID);
            ConsoleHelper.writeMessage("\nРабочая группа удалена!\n");
            break;
        case 3:
            ConsoleHelper.writeMessage("Укажите ID рабочей группы:\n");
            teamID = ConsoleHelper.readInt();
            ConsoleHelper.writeMessage("\nУкажите новое имя рабочей группы:\n");
            name = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Укажите ID проекта:\n");
            projectID = ConsoleHelper.readInt();
            teamDao.updateElement(teamID, name, projectID);
            ConsoleHelper.writeMessage("\nИзменения выполнены!\n");
            break;
        case 4:
            teamDao.showAllTeams();
            break;
        case 5:
            ConsoleHelper.writeMessage("Укажите рабочей группы:\n");
            teamID = ConsoleHelper.readInt();
            teamDao.showTeam(teamID);
    }
}
}
