package ProjectSystem.factory;

import ProjectSystem.dao.DeveloperDao;

public class DeveloperFactory{

    public int createDeveloper(String name) {
        DeveloperDao developerDao = new DeveloperDao();
        int developerID = developerDao.createElement(name);
        return developerID;
    }
}
