import java.util.ArrayList;

public class TeacherArrList {
    private static ArrayList<Teacher> teachers = new ArrayList<>();

    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public static void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public static ArrayList<Teacher> getTeachersOfSubject(String subject) {
        ArrayList<Teacher> teachersOfSubject = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getSubject().equals(subject)) {
                teachersOfSubject.add(teacher);
            }
        }
        return teachersOfSubject;
    }

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

    public static Teacher getTeacher(String teacherName) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(teacherName)) {
                return teacher;
            }
        }
        return null;
    }
}

