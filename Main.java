import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        //Add teachers to the arrayList of Teachers
        Teacher msAshkarMATH = new Teacher("Ms.Ashkar (Math)", "MATH", "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNjg0MTg1NjY3LCJqdGkiOiJhZDgzMmQ3OC1hZDIzLTQ1MDktYTA3ZS00NmNlMzcwM2ZiNmYiLCJ1c2VyX3V1aWQiOiJhNDkwZDk5MC04YmM5LTRjNzItYmY4OC00OThmNDAzMTk4M2MifQ.eTg2VWRnbMRjYv-J5XBng_MsgtfTcMqpf86egnVdGIxa6jLT3mtWECDymaiapINtv25JdVGgBzhJ0OpFjFfDpA");
        Teacher msAshkarCSA = new Teacher("Ms.Ashkar (CSA)", "CSA", "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNjg0MTg1NjY3LCJqdGkiOiJhZDgzMmQ3OC1hZDIzLTQ1MDktYTA3ZS00NmNlMzcwM2ZiNmYiLCJ1c2VyX3V1aWQiOiJhNDkwZDk5MC04YmM5LTRjNzItYmY4OC00OThmNDAzMTk4M2MifQ.eTg2VWRnbMRjYv-J5XBng_MsgtfTcMqpf86egnVdGIxa6jLT3mtWECDymaiapINtv25JdVGgBzhJ0OpFjFfDpA");
        Teacher msAlex = new Teacher("Alex", "CSA", "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNjg0MTg1NjY3LCJqdGkiOiJhZDgzMmQ3OC1hZDIzLTQ1MDktYTA3ZS00NmNlMzcwM2ZiNmYiLCJ1c2VyX3V1aWQiOiJhNDkwZDk5MC04YmM5LTRjNzItYmY4OC00OThmNDAzMTk4M2MifQ.eTg2VWRnbMRjYv-J5XBng_MsgtfTcMqpf86egnVdGIxa6jLT3mtWECDymaiapINtv25JdVGgBzhJ0OpFjFfDpA");
        Teacher mrGarcia = new Teacher("Mr.Garcia", "MATH", "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNjg0MTg1NjY3LCJqdGkiOiJhZDgzMmQ3OC1hZDIzLTQ1MDktYTA3ZS00NmNlMzcwM2ZiNmYiLCJ1c2VyX3V1aWQiOiJhNDkwZDk5MC04YmM5LTRjNzItYmY4OC00OThmNDAzMTk4M2MifQ.eTg2VWRnbMRjYv-J5XBng_MsgtfTcMqpf86egnVdGIxa6jLT3mtWECDymaiapINtv25JdVGgBzhJ0OpFjFfDpA");
        
        TeacherArrList.addTeacher(msAshkarCSA);
        TeacherArrList.addTeacher(msAshkarMATH);
        TeacherArrList.addTeacher(msAlex);
        TeacherArrList.addTeacher(mrGarcia);
        
        Boolean status = true;
        while(status) {
            System.out.println();
            System.out.println();
            System.out.println(); 

            Scanner input = new Scanner(System.in);

            System.out.println("Welcome to Understood the Assignment!");
            System.out.println("The Understood the Assignment app is a program that allows students to schedule appointments with teachers.");


            // Get the teachers of the subject the user wants help with
            System.out.println("What subject would you like help with?");
            String subject = input.nextLine();
            subject = subject.toUpperCase();
            
            //Print the schedule of the teachers of the subject the user wants help with
            TeacherArrList.printTeachersSchedule(subject);

            //Get the teacher the user wants to schedule an appointment with

            if (TeacherArrList.printTeachersSchedule(subject) == false) {
                //Error message
                System.out.println("Sorry, there are no teachers available for that subject.");
                //Prompt user to try again
                System.out.println("Would you like to try again? (Y/N)");
                String answer = input.nextLine();
                answer = answer.toUpperCase();
                if (answer.equals("N")) {
                    break;

                }
            } 
            else {
                System.out.println("Which teacher would you like to schedule an appointment with? (Please copy and paste the exact name that appears)");
                String teacherName = input.nextLine();
                Teacher tutor = TeacherArrList.getTeacher(teacherName);
                tutor.bookMeeting();
                System.out.println("Once you have follwed through the Calendly link, your appointment has been booked!");
                System.out.println("Would you like to schedule another appointment? (Y/N)");
                String answer = input.nextLine();
                answer = answer.toUpperCase();
                if (answer.equals("N")) {
                    status = false;
                }
            }
        }
        System.out.println("Thank you for booking with Understood the Assignment!");
    }
    
}