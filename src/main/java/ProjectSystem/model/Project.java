package ProjectSystem.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project implements Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectID")
    private int projectID;

    @Column(name= "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "projects_teams", joinColumns = @JoinColumn(name = "projectID"),
              inverseJoinColumns = @JoinColumn(name = "teamID"))
    private Set<Team> teams;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        String listTeams = "";
        if(teams != null)
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
