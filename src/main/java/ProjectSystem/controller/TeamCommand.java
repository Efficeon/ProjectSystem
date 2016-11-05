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
        int developerID;

        ConsoleHelper.writeMessage("* * * РАБОЧИЕ ГРУППЫ РАЗРАБОТЧИКОВ * * *" + "\n" +
                "1 - Добавить | 2 - Удалить | 3 - Изменить | 4 - Добавить разработчика в рабочую группу | " +
                "5 - Показать все | 6 - Найти по ID\n");
    int commandNumber = ConsoleHelper.readInt();

        switch (commandNumber){
        case 1:
            ConsoleHelper.writeMessage("Укажите название рабочей группы:\n");
            name = ConsoleHelper.readString();
            teamID = teamFactory.createTeam(name);
            teamDao.showTeam(teamID);
            ConsoleHelper.writeMessage("\nРабочая группа создана!\n");
            break;
        case 2:
            ConsoleHelper.writeMessage("Укажите ID рабочей группы:\n");
            teamID = ConsoleHelper.readInt();
            try {
                teamDao.deleteElement(teamID);
                ConsoleHelper.writeMessage("\nРабочая группа удалена!\n");
            }
            catch (IllegalArgumentException e){
                ConsoleHelper.writeMessage("Разработчик с указаным ID отсутствует.");
            }
            break;
        case 3:
            ConsoleHelper.writeMessage("Укажите ID рабочей группы:\n");
            teamID = ConsoleHelper.readInt();
            ConsoleHelper.writeMessage("\nУкажите новое имя рабочей группы:\n");
            name = ConsoleHelper.readString();
            try {
                teamDao.updateElement(teamID, name);
                teamDao.showTeam(teamID);
                ConsoleHelper.writeMessage("\nИзменения выполнены!\n");
            } catch (NullPointerException e){
                ConsoleHelper.writeMessage("Введены некорректные данные.");
            }
            break;
        case 4:
            ConsoleHelper.writeMessage("Укажите ID рабочей группы:\n");
            teamID = ConsoleHelper.readInt();
            ConsoleHelper.writeMessage("Укажите ID разработчика:\n");
            developerID = ConsoleHelper.readInt();
            try {
                teamDao.addDeveloper(teamID, developerID);
                teamDao.showTeam(teamID);
                ConsoleHelper.writeMessage("\nРазработчик добавлен!\n");
            } catch (NullPointerException e){
                ConsoleHelper.writeMessage("Введены некорректные данные.");
            }
            break;
        case 5:
            teamDao.showAllTeams();
            break;
        case 6:
            ConsoleHelper.writeMessage("Укажите ID рабочей группы:\n");
            teamID = ConsoleHelper.readInt();
            try {
                teamDao.showTeam(teamID);
            } catch (NullPointerException e){
                    ConsoleHelper.writeMessage("Введены некорректные данные.");
            }
        }
    }
}
