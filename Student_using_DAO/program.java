package Student_using_DAO;

import java.util.Scanner;

public class program {
  
    public static void main(String[] args) {  
         
        Scanner scanner = new Scanner(System.in);
        System.out.println("Connecting to the database...");
        // Initialize database connection
        try {
            IStudent S = new StudentDAOImpl();
            System.out.println("Connected to the database successfully.");
            
            while (true) {
                System.out.println("\nChoose an operation to perform:");
                System.out.println("1. Add Student");
                System.out.println("2. Update Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Get Student by ID");
                System.out.println("5. Get All Students");
                System.out.println("6. Cancel");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String nameToAdd = scanner.nextLine();
                        S.AddStudent(new Student(0, nameToAdd)); // ID will be auto-generated, so set it to 0
                        break;
                    case 2:
                        System.out.print("Enter student ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter new student name: ");
                        String newName = scanner.nextLine();
                        S.UpdateStudent(new Student(updateId, newName));
                        break;
                    case 3:
                        System.out.print("Enter student ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        S.DeleteStudent(new Student(deleteId, ""));
                        break;
                    case 4:
                        System.out.print("Enter student ID to get: ");
                        int getId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        Student student = S.getStudent(getId);
                        if (student != null)
                            System.out.println("Student: [ID : " + student.getStudent_ID() + ", Name : " + student.getName() + " ]");
                        else
                            System.out.println("Student with ID " + getId + " not found.");
                        break;
                    case 5:
                        for (Student s : S.GetAllStudents()) {  
                            System.out.println("Student: [ID : " + s.getStudent_ID() + ", Name : " + s.getName() + " ]");      
                        }
                        break;
                    case 6:
                        System.out.println("Operation cancelled.");
                        scanner.close();
                        return; // Exit the program
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
