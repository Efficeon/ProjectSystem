package ProjectSystem.model;

public class Developer implements Model{
    private int developerID;
    private String name;
    private int teamID;

    public Developer() {
    }

    public Developer(int developerID, String name,int teamID) {
        this.developerID = developerID;
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

    @Override
    public String toString() {
        return "-----------------------------------------------------------------------------------------------" + "\n" +
                "Разработчик: " + name + "\n" +
                "ID разработчика: " + developerID + "\n" +
                "ID команды разработчика: " + teamID;
    }
}
