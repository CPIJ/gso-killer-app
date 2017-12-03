package data.repository;

import data.model.Project;
import org.junit.Test;

public class MysqlProjectRepositoryTest {


    @Test
    public void insert() {
        MysqlProjectRepository repository = new MysqlProjectRepository();
        Project p = new Project("Test");
    }
}