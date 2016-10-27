package ProjectSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Team implements Model{
    private int teamID;
    private String name;
    private List<Developer> teamOfDeveloper = null;
    private int projectID;

    public Team() {
    }

    public Team(int teamID, String name, int projectID) {
        this.teamID = teamID;
        this.name = name;
        this.projectID = projectID;

        this.teamOfDeveloper = new ArrayList<>();
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public List<Developer> getTeamOfDeveloper() {
        return teamOfDeveloper;
    }

    public void setTeamOfDeveloper(List<Developer> teamOfDeveloper) {
        this.teamOfDeveloper = teamOfDeveloper;
    }

    @Override
    public String toString() {
        String listSDeveloper = "";
        for (Developer developer : teamOfDeveloper){
            listSDeveloper += "ID разработчика: " + developer.getDeveloperID() +
                              " ФИО: " + developer.getName() + "\n";
        }

        return  "-----------------------------------------------------------------------------------------------" + "\n" +
                "Имя группы разработчиков: " + name + "; " +"\n" +
                "Команда разработчиков: " + "\n" +
                "ID команды: " + teamID + "\n" +
                "Разработчики: " + "\n" + listSDeveloper;
    }
}
