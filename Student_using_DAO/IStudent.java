package Student_using_DAO;

import java.util.*;

public interface IStudent {
    //Create
    public void AddStudent(Student s);
    //Read
    public List<Student> GetAllStudents();
    //Read2
    public Student getStudent(int ID);
    //Update
    public void  UpdateStudent (Student s);
    //Delete
    public void  DeleteStudent (Student s);


}  
