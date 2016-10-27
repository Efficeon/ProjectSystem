package ProjectSystem.controller;

import ProjectSystem.dao.DeveloperDao;
import ProjectSystem.factory.DeveloperFactory;
import ProjectSystem.view.ConsoleHelper;

import java.io.IOException;
import java.sql.SQLException;

public class DeveloperCommand implements Command{

    @Override
    public void execute() throws IOException, ClassNotFoundException, SQLException {
        DeveloperFactory developerFactory = new DeveloperFactory();
        DeveloperDao developerDao = new DeveloperDao();

        String name;
        int developerID;
        int teamID;

        ConsoleHelper.writeMessage("* * * РАЗРАБОТЧИКИ * * *" + "\n" +
                "1 - Добавить | 2 - Удалить | 3 - Изменить | 4 - Показать всех | 5 - Найти по ID\n");
        int commandNumber = ConsoleHelper.readInt();

        switch (commandNumber){
            case 1:
                ConsoleHelper.writeMessage("Укажите инициалы(Имя и Фамилию) разработчика:\n");
                name = ConsoleHelper.readString();
                ConsoleHelper.writeMessage("\nУкажите ID разработчика:\n");
                developerID = ConsoleHelper.readInt();
                ConsoleHelper.writeMessage("\nУкажите ID рабочей группы за которой будет закреплен разработчик:\n");
                teamID = ConsoleHelper.readInt();
                ConsoleHelper.writeMessage("\nРазработчик создан!\n");

                developerFactory.createDeveloper(developerID, name, teamID);
                developerDao.showDeveloper(developerID);
                break;
            case 2:
                ConsoleHelper.writeMessage("Укажите ID разработчика:\n");
                developerID = ConsoleHelper.readInt();
                developerDao.deleteElement(developerID);
                ConsoleHelper.writeMessage("\nРазработчик удален!\n");
                break;
            case 3:
                ConsoleHelper.writeMessage("Укажите ID разработчика:\n");
                developerID = ConsoleHelper.readInt();
                ConsoleHelper.writeMessage("\nУкажите ID новой рабочей группы:\n");
                teamID = ConsoleHelper.readInt();
                developerDao.updateElement(developerID, teamID);
                ConsoleHelper.writeMessage("\nИзменения выполнены!\n");
                break;
            case 4:
                    developerDao.showAllDevelopers();
                break;
            case 5:
                ConsoleHelper.writeMessage("Укажите ID разработчика:\n");
                developerID = ConsoleHelper.readInt();
                developerDao.showDeveloper(developerID);
                break;
            default:
                break;
        }
    }
}
