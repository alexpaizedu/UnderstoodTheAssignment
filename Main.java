import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        //In this class, users interact through their input in order to book meetings.
        
        
        
        //Add teachers to the arrayList of Teachers
        
        //Note, if a teacher teaches two subjects, they are listed twice but with a different subject in the parameter.
        //Also note, the api key put here is my own. When the program actually runs, each teacher would place their unique api key so their Calendly account is shown. 
        Teacher msAshkarMATH = new Teacher("Ms.Ashkar (Math)", "MATH", "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNjg0MTg1NjY3LCJqdGkiOiJhZDgzMmQ3OC1hZDIzLTQ1MDktYTA3ZS00NmNlMzcwM2ZiNmYiLCJ1c2VyX3V1aWQiOiJhNDkwZDk5MC04YmM5LTRjNzItYmY4OC00OThmNDAzMTk4M2MifQ.eTg2VWRnbMRjYv-J5XBng_MsgtfTcMqpf86egnVdGIxa6jLT3mtWECDymaiapINtv25JdVGgBzhJ0OpFjFfDpA");
        Teacher msAshkarCSA = new Teacher("Ms.Ashkar (CSA)", "CSA", "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNjg0MTg1NjY3LCJqdGkiOiJhZDgzMmQ3OC1hZDIzLTQ1MDktYTA3ZS00NmNlMzcwM2ZiNmYiLCJ1c2VyX3V1aWQiOiJhNDkwZDk5MC04YmM5LTRjNzItYmY4OC00OThmNDAzMTk4M2MifQ.eTg2VWRnbMRjYv-J5XBng_MsgtfTcMqpf86egnVdGIxa6jLT3mtWECDymaiapINtv25JdVGgBzhJ0OpFjFfDpA");
        Teacher msAlex = new Teacher("Alex", "CSA", "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNjg0MTg1NjY3LCJqdGkiOiJhZDgzMmQ3OC1hZDIzLTQ1MDktYTA3ZS00NmNlMzcwM2ZiNmYiLCJ1c2VyX3V1aWQiOiJhNDkwZDk5MC04YmM5LTRjNzItYmY4OC00OThmNDAzMTk4M2MifQ.eTg2VWRnbMRjYv-J5XBng_MsgtfTcMqpf86egnVdGIxa6jLT3mtWECDymaiapINtv25JdVGgBzhJ0OpFjFfDpA");
        Teacher mrGarcia = new Teacher("Mr.Garcia", "MATH", "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNjg0MTg1NjY3LCJqdGkiOiJhZDgzMmQ3OC1hZDIzLTQ1MDktYTA3ZS00NmNlMzcwM2ZiNmYiLCJ1c2VyX3V1aWQiOiJhNDkwZDk5MC04YmM5LTRjNzItYmY4OC00OThmNDAzMTk4M2MifQ.eTg2VWRnbMRjYv-J5XBng_MsgtfTcMqpf86egnVdGIxa6jLT3mtWECDymaiapINtv25JdVGgBzhJ0OpFjFfDpA");
        
        TeacherArrList.addTeacher(msAshkarCSA);
        TeacherArrList.addTeacher(msAshkarMATH);
        TeacherArrList.addTeacher(msAlex);
        TeacherArrList.addTeacher(mrGarcia);
        
        
        
        Boolean status = true;
        
        //The code works inside a while loop so that the user can book meetings multiple times until they exit.
        
        while(status) {
            System.out.println();
            System.out.println();
            System.out.println(); 

            Scanner input = new Scanner(System.in);
        
            System.out.println("Welcome to Understood the Assignment!");
            System.out.println("The Understood the Assignment app is a program that allows students to schedule appointments with teachers.");
 
}
