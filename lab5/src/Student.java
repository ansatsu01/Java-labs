public class Student implements Comparable<Student> {
    int age;
    String name;
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(Student o) {
        if(this.age == o.age){
            return this.name.compareTo(o.name);
        }
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return name + "-" + age + " y.o";
    }
}
