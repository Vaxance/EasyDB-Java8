package easyDB.repositories;

import easyDB.EasyDB;
import easyDB.repositories.anotaions.Field;
import easyDB.repositories.anotaions.Fields;
import easyDB.repositories.anotaions.RepositoryTable;
import easyDB.repositories.dataBasedRepos.Repository;
import easyDB.repositories.objekdBasedRepos.ObjektRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
public class RepositoryManager {

    public RepositoryManager() {
    }

    public void addRepository(Repository repository) {
        createTable(repository);
    }

    public void addRepository(ObjektRepository repository) {
        EasyDB.getInstance().getMySQLConnector().createTable(repository.getClass().getDeclaredAnnotation(RepositoryTable.class).tableName());
    }

    public void createTable(Repository repository) {
        List<String> fields = new ArrayList<>();

        for (Field field : repository.getClass().getDeclaredAnnotation(Fields.class).fields()) {
            fields.add(field.fieldName());
        }

        EasyDB.getInstance().getMySQLConnector().createTable(repository.getClass().getDeclaredAnnotation(RepositoryTable.class).tableName(), fields.toArray(new String[0]));
    }

}
