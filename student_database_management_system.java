import java.util.*; //student database management system using inheritance,exception handling,comline argument,file handling,interface,constructor overloading, switching 
import java.io.*;
class Admission  extends Thread{
    //public static int num;
    Scanner sc = new Scanner(System.in);
    public String name;
    public int roll;
    public String course;
    public int sub1;
    public int sub2;
    public int sum;
    public double percent;
    Admission(){};
    Admission(String name,int roll,String coure,int num1,int num2,int sum,double percent)
    {
        this.course=coure;
        this.name=name;
        this.roll=roll;
        this.sub1=num1;
        this.sub2=num2;
        this.sum=sum;
        this.percent=percent;
    };

    void add_student_details() 
    {
        System.out.println("\n-------------------------------------------------------------\n");
        System.out.println("Please enter the details of the students-\n");
            System.out.println("Student  Name: ");
            name = sc.next();
            System.out.println("Student  Roll: ");
            roll = sc.nextInt();
            System.out.println("Student  Course: ");
            course= sc.next();
            System.out.println("\n-------------------------------------------------------------\n");
    }
}
interface Sum
{
    public void Sumsub(int num1,int num2);
}
class Exam extends Admission  implements Sum{
    public void input_exam_data() 
    {
        System.out.println("\n-------------------------------------------------------------\n");    
        System.out.println("Student  Subject1 Number: ");
        sub1 = sc.nextInt();
        System.out.println("Student  Subject2 Number: ");
        sub2= sc.nextInt();
        System.out.println("\n-------------------------------------------------------------\n");
    }
    public void Sumsub(int num1,int num2)
    {
        sum=num1+num2;
        percent=(sum/200.0)*100;
    }
}

public class Abhishek_Sir 
{
    static ArrayList<Admission> Students=new ArrayList<Admission>(); //dynamically make Sutdents array 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        if (args.length >0) //count the args which is pass by the terminal
        {
            load_Students(args[0]);
        }
        int ch;
        while (true) {
            System.out.println(" 1.Students Admission \n 2. Save Students data \n 3. Students Details \n 4. Student Result Data \n 5. Save Student Result \n 6. Students Certificate \n 7.exit\n");
            System.out.println("Which operation you want to perform: ");
            ch = sc.nextInt(); //get input of the choice 
            switch (ch) {
                case 1:
                    add_student(); //get students data
                    break;
                case 2:
                    save_data(args[0]); //save student data
                    break;
                case 3:
                    students_details(); //display student data
                    break;
                case 4:
                    input_exam(); //get students exam data
                    break;
                case 5:
                    save_data(args[0]); //save student result data
                    break;
                case 6:
                    Certificate(); //print all the student certificate
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    public static void add_student()
    {
        Admission ad = new Admission();
        ad.add_student_details(); //function call
        Students.add(ad); //add student details into the Students array
    }
    public static void input_exam()
    {
        int n=Students.size();
        for (int i=0;i<n;i++) 
        {
            Exam em=new Exam();
            System.out.println("Student name: "+ Students.get(i).name + "\nStudent Roll: " + Students.get(i).roll + "\nStudent Course: " +Students.get(i).course +"\n");
            em.input_exam_data(); //fuction call
            Students.get(i).sub1 =em.sub1; //update subject1 number into Students array
            Students.get(i).sub2 =em.sub2; //update subject2 number into Students array
            em.Sumsub(Students.get(i).sub1, Students.get(i).sub2);
            Students.get(i).sum =em.sum; //update Sum of number into Students array
            Students.get(i).percent =em.percent; //update percent of number into Students array
        }
    }
    public static void Certificate() // print all the student Students
    {
        System.out.println("\n-------------------------------------------------------------\n");
        for (Admission Student:Students ) 
        {
            System.out.println("Student name: "+ Student.name + "\nStudent Roll: " + Student.roll + "\nStudent Course: " +Student.course +"\nStudent 1st Subject: "+Student.sub1+"\nStudent 2nd Subject: "+Student.sub2+"\nStudent  total Number: " + Student.sum+"\nStudent  Percentage: " + Student.percent+"\n");
        }
        System.out.println("\n-------------------------------------------------------------\n");
    }
    public static void students_details() // print all the student data
    {
        System.out.println("\n-------------------------------------------------------------\n");
        for (Admission Student:Students) {
            System.out.println("Student name: "+ Student.name + "\nStudent Roll: " + Student.roll + "\nStudent Course: " +Student.course +"\n");
        }
        System.out.println("\n-------------------------------------------------------------\n");
    }

    public static void load_Students(String fileName)
    {
        try{
            File file = new File(fileName);
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()) //read the existing file
            {
                String line=sc.nextLine();
                String []data= line.split(" ");
                Admission st=new Admission(data[0], Integer.parseInt(data[1]),data[2],Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5]),Double.parseDouble(data[6])); //call the Admission constructor
                Students.add(st);
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(" New file ");
        } 
    }
    public static void save_data(String filename)
    {
        try
        {
            FileWriter fileWriter=new FileWriter(filename);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter); // write student data into the file
            for( Admission stu: Students)
            {
                bufferedWriter.write(stu.name + " " + stu.roll + " " + stu.course + " "+stu.sub1+" "+stu.sub2+" "+ stu.sum+" "+stu.percent+"\n");
            }
            bufferedWriter.close();
        } 
        catch ( IOException e)
        {
            System.out.println("Error");
        }
    }
}