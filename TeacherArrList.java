import java.util.ArrayList;

//This is a class that contains methods for an arraylist of all the teachers listed in the program.

public class TeacherArrList {
    private static ArrayList<Teacher> teachers = new ArrayList<>();

    //This method adds teachers.
    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
    
    
    //This method removes teachers.
    public static void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }
    
    
    //This method filters teachers by subject.
    public static ArrayList<Teacher> getTeachersOfSubject(String subject) {
        ArrayList<Teacher> teachersOfSubject = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getSubject().equals(subject)) {
                teachersOfSubject.add(teacher);
            }
        }
        return teachersOfSubject;
    }
    
    
    //This method prints the schedules of each teacher of the selected subject.
    public static Boolean printTeachersSchedule(String subject) {
        ArrayList<Teacher> teachersOfSubject = getTeachersOfSubject(subject);
        for (Teacher teacher : teachersOfSubject) {
            teacher.getAvailability();
            System.out.println();
        }
        if (teachersOfSubject.size() == 0) {
            System.out.println("No teachers found for subject: " + subject);
            System.out.println("Press enter to continue");
            return false;
        }
        return true;
    }

    //This method returns the teacher chosen by from the arraylist.
    public static Teacher getTeacher(String teacherName) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(teacherName)) {
                return teacher;
            }
        }
        return null;
    }
}

