package ProjectSystem.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer implements Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "developerID")
    private int developerID = 0;

    @Column(name = "name")
    private String name;

    @Column(name = "teamID")
    private int teamID;

    @ManyToMany(mappedBy = "developers")
    private Set<Team> teams;

    public Developer() {
    }

    public Developer(String name, int teamID) {
        this.name = name;
        this.teamID = teamID;
    }

    public int getDeveloperID() {
        return developerID;
    }

    public void setDeveloperID(int developerID) {
        this.developerID = developerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "-----------------------------------------------------------------------------------------------" + "\n" +
                "Разработчик: " + name + "\n" +
                "ID разработчика: " + developerID + "\n" +
                "ID команды разработчика: " + teamID;
    }
}
