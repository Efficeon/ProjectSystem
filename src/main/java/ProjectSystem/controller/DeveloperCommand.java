package ProjectSystem.controller;

import ProjectSystem.dao.DeveloperDao;
import ProjectSystem.factory.DeveloperFactory;
import ProjectSystem.view.ConsoleHelper;
import javassist.tools.rmi.ObjectNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

public class DeveloperCommand implements Command{

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        DeveloperFactory developerFactory = new DeveloperFactory();
        DeveloperDao developerDao = new DeveloperDao();

        String name;
        int developerID;

        ConsoleHelper.writeMessage("* * * РАЗРАБОТЧИКИ * * *" + "\n" +
                "1 - Добавить | 2 - Удалить | 3 - Изменить | 4 - Показать всех | 5 - Найти по ID\n");
        int commandNumber = ConsoleHelper.readInt();

        switch (commandNumber){
            case 1:
                ConsoleHelper.writeMessage("Укажите инициалы(Имя и Фамилию) разработчика:\n");
                name = ConsoleHelper.readString();
                ConsoleHelper.writeMessage("\nРазработчик создан!\n");
                developerID = developerFactory.createDeveloper(name);
                developerDao.showDeveloper(developerID);
                break;
            case 2:
                ConsoleHelper.writeMessage("Укажите ID разработчика:\n");
                developerID = ConsoleHelper.readInt();
                try {
                    developerDao.deleteElement(developerID);
                    ConsoleHelper.writeMessage("\nРазработчик удален!\n");
                }
                catch (IllegalArgumentException e){
                ConsoleHelper.writeMessage("Разработчик с указаным ID отсутствует.");
            }
                break;
            case 3:
                ConsoleHelper.writeMessage("Укажите ID разработчика:\n");
                developerID = ConsoleHelper.readInt();
                ConsoleHelper.writeMessage("\nУкажите новое имя разработчика:\n");
                name = ConsoleHelper.readString();
                try {
                    developerDao.updateElement(developerID, name);
                    ConsoleHelper.writeMessage("\nИзменения выполнены!\n");
                } catch (NullPointerException e){
                    ConsoleHelper.writeMessage("Введены некорректные данные.");
                }
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
