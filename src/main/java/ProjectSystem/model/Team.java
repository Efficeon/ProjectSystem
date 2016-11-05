package ProjectSystem.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team implements Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teamID")
    private int teamID;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "teams_developers", joinColumns = @JoinColumn(name = "teamID"),
              inverseJoinColumns = @JoinColumn(name = "developerID"))
    private Set<Developer> developers;


    @ManyToMany(mappedBy = "teams")
    protected Set<Project> projects;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
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

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    @Override
    public String toString() {

        String listDeveloper = "";
        for (Developer developer : developers){
            listDeveloper += "ID разработчика: " + developer.getDeveloperID() +
                              " ФИО: " + developer.getName() + "\n";
        }

        String listProject = "";
            for (Project project : projects){
                listProject += "ID проекта: " + project.getProjectID() +
                        " название: " + project.getName() + "\n";
            }
        return  "-----------------------------------------------------------------------------------------------" + "\n" +
                "Имя группы разработчиков: " + name + "; " +"\n" +
                "Команда разработчиков: " + "\n" +
                "ID команды: " + teamID + "\n" +
                "Разработчики: " + "\n" + listDeveloper + "\n" +
                "Проекты: " + "\n" + listProject;
    }
}
