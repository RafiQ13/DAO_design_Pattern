package Student_using_DAO;

public class Student {
    public int Student_ID;
    public String name;

//constructor
public Student(int Student_ID,String name)
{    
    this.Student_ID = Student_ID;
    this.name = name;
}
//getters
public String getName() 
{
    return name;
}

public int getStudent_ID()
{ 
    return Student_ID; 
}
 
//setters
public void setName(String name) 
{
    this.name = name;
}

public void setStudent_ID(int Student_ID)
{
    this.Student_ID = Student_ID;
}

}
