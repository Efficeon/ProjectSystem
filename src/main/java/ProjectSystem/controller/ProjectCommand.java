package ProjectSystem.controller;

import ProjectSystem.dao.ProjectDao;
import ProjectSystem.factory.ProjectFactory;
import ProjectSystem.view.ConsoleHelper;

import java.io.IOException;
import java.sql.SQLException;

public class ProjectCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException, SQLException {
        ProjectFactory projectFactory = new ProjectFactory();
        ProjectDao projectDao = new ProjectDao();

        String name;
        int projectID;
        int teamID;

        ConsoleHelper.writeMessage("* * * ПРОЕКТЫ * * *" + "\n" +
                "1 - Добавить | 2 - Удалить | 3 - Изменить | 4 - Показать все | 5 - Найти по ID\n");
        int commandNumber = ConsoleHelper.readInt();

        switch (commandNumber){
            case 1:
                ConsoleHelper.writeMessage("Укажите название проекта:\n");
                name = ConsoleHelper.readString();
                projectFactory.createProject(name);
                ConsoleHelper.writeMessage("\nПроект создан!\n");
                break;
            case 2:
                ConsoleHelper.writeMessage("Укажите ID проекта:\n");
                projectID = ConsoleHelper.readInt();
                projectDao.deleteElement(projectID);
                ConsoleHelper.writeMessage("\nПроект удален!\n");
                break;
            case 3:
                ConsoleHelper.writeMessage("Укажите ID проекта:\n");
                projectID = ConsoleHelper.readInt();
                ConsoleHelper.writeMessage("\nУкажите новое имя проекта:\n");
                name = ConsoleHelper.readString();
                ConsoleHelper.writeMessage("Укажите ID рабочей группы:\n");
                teamID = ConsoleHelper.readInt();
                projectDao.updateElement(projectID, name, teamID);
                ConsoleHelper.writeMessage("\nИзменения выполнены!\n");
                break;
            case 4:
                projectDao.showAllProjects();
                break;
            case 5:
                ConsoleHelper.writeMessage("Укажите ID проекта:\n");
                projectID = ConsoleHelper.readInt();
                projectDao.showProject(projectID);
        }
    }
}
