package Student_using_DAO;

import java.util.*;
import java.sql.*;

public class StudentDAOImpl implements IStudent {

    private Connection connection;

    public StudentDAOImpl() {
        // Initialize database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?user=root");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AddStudent(Student s) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Student (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, s.getName());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    s.setStudent_ID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
            }

            System.out.println("Student " + s.getName() + " added to the database with ID " + s.getStudent_ID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> GetAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Student";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Student_ID");
                String name = resultSet.getString("Name");
                students.add(new Student(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void UpdateStudent(Student s) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Student SET Name = ? WHERE Student_ID = ?")) {
            statement.setString(1, s.getName());
            statement.setInt(2, s.getStudent_ID());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Student with ID " + s.getStudent_ID() + " not found in the database");
            } else {
                System.out.println("Student with ID " + s.getStudent_ID() + " has been updated in the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteStudent(Student s) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Student WHERE Student_ID = ?")) {
            statement.setInt(1, s.getStudent_ID());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("Student with ID " + s.getStudent_ID() + " not found in the database");
            } else {
                System.out.println("Student with ID " + s.getStudent_ID() + " has been deleted from the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudent(int ID) {
        Student student = null;
        String query = "SELECT * FROM Student WHERE Student_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("Student_ID");
                    String name = resultSet.getString("Name");
                    student = new Student(id, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
