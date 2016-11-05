package ProjectSystem.controller;

import ProjectSystem.dao.ProjectDao;
import ProjectSystem.factory.ProjectFactory;
import ProjectSystem.view.ConsoleHelper;
import org.hibernate.ObjectNotFoundException;

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
                "1 - Добавить | 2 - Удалить | 3 - Изменить | 4 - Добавить рабочую группу к проекту | " +
                "5 - Показать все | 6 - Найти по ID\n");
        int commandNumber = ConsoleHelper.readInt();

        switch (commandNumber){
            case 1:
                ConsoleHelper.writeMessage("Укажите название проекта:\n");
                name = ConsoleHelper.readString();
                projectID = projectFactory.createProject(name);
                projectDao.showProject(projectID);
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
                try {
                    projectDao.updateElement(projectID, name);
                    projectDao.showProject(projectID);
                    ConsoleHelper.writeMessage("\nИзменения выполнены!\n");
                } catch (NullPointerException e){
                    ConsoleHelper.writeMessage("Введены некорректные данные.");
                }
                break;
            case 4:
                ConsoleHelper.writeMessage("Укажите ID проекта:\n");
                projectID = ConsoleHelper.readInt();
                ConsoleHelper.writeMessage("Укажите ID рабочей группы:\n");
                teamID = ConsoleHelper.readInt();
                try {
                    projectDao.addTeam(projectID, teamID);
                    projectDao.showProject(projectID);
                    ConsoleHelper.writeMessage("\nРабочая группа добавлена!\n");
                } catch (NullPointerException e){
                    ConsoleHelper.writeMessage("Введены некорректные данные.");
                }
                break;
            case 5:
                projectDao.showAllProjects();
                break;
            case 6:
                ConsoleHelper.writeMessage("Укажите ID проекта:\n");
                projectID = ConsoleHelper.readInt();
                try {
                    projectDao.showProject(projectID);
                } catch (ObjectNotFoundException e){
                    ConsoleHelper.writeMessage("Проект с указаным ID отсутствует.");
                }
        }
    }
}
