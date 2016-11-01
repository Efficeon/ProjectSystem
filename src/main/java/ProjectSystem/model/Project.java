package ProjectSystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project implements Model{

    @Id
    @Column(name = "projectID")
    private int projectID;

    @Column(name= "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Team> teams;

    public Project() {
    }

    public Project(int projectID, String name) {
        this.projectID = projectID;
        this.name = name;
        this.teams = new ArrayList<>();
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        String listTeams = "";
        for (Team team : teams){
            listTeams += "ID группы: " + team.getTeamID() +
                         " Название группы: " + team.getName() + "\n";
        }
        return  "-----------------------------------------------------------------------------------------------" + "\n" +
                "Название проекта: " + name + "; " + "\n" +
                "ID проекта: " + projectID + "\n" +
                "Рабочие группы: " + "\n" + listTeams;
    }
}
