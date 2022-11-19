import java.util.HashMap;

public class Student implements Comparable<Student>{
    private final String name;
    private final int id;
    private final int sem;
    private final String subject;
    private final int grade;

    Student(String name, int id, int sem, String subject, int grade){
        this.name = name;
        this.id = id;
        this.sem = sem;
        this.subject = subject;
        this.grade = grade;
    }
    int getId(){return this.id;}
    int getSem(){
        return this.sem;
    }
    boolean exists(String name, int id, int sem, String subject){
        return this.name.equals(name) && this.id==id && this.sem == sem && this.subject.equals(subject);
    }
    String getSubject(){
        return this.subject;
    }

    int getGrade(){
        return this.grade;
    }

    @Override
    public int compareTo(Student o) {
        if(!this.subject.equals(o.subject)){
            return this.subject.compareTo(o.subject);
        }else {
            return this.name.compareTo(o.name);
        }
    }

    @Override
    public String toString(){
        return "Student " + name + ", id: " + id + ", sem: " + sem + " , grades: " + subject + " " + grade;
    }
}
