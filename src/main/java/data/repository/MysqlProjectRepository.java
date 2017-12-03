package data.repository;

import data.model.Project;

import java.sql.*;

public class MysqlProjectRepository implements IProjectRepository {
    Connection connection;

    public MysqlProjectRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/syncoder?useSSL=false", "root", "mast3r1k0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project find(String id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM project WHERE id = ?")) {
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            Project p = null;

            if (resultSet.next()) {
                p = new Project(resultSet.getString("id"));
                p.setContent(resultSet.getString("content"));
            }
            return p;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Project project) {
        boolean projectAlreadyExists = find(project.getId()) != null;
        int affectedRows = -1;

        if (projectAlreadyExists) {
            affectedRows = update(project);
        } else {
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO project VALUES(?, ?)")) {

                statement.setString(1, project.getId());
                statement.setString(2, project.getContent());

                affectedRows = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return affectedRows > 0;
    }

    @Override
    public boolean delete(Project project) {
        return false;
    }

    private int update(Project project) {
        int affectedRows = -1;

        try(PreparedStatement statement = connection.prepareStatement("UPDATE project SET content = ? WHERE id LIKE ?")){

            statement.setString(1, project.getContent());
            statement.setString(2, project.getId());

            affectedRows = statement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return affectedRows;
    }
}
