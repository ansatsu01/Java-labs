public class Main {
    public static void main(String[] args) {

            Tree<Integer> integerTree = new Tree<>();
            integerTree.add(20);
            integerTree.add(17);
            integerTree.add(13);
            integerTree.add(19);
            integerTree.add(24);
            integerTree.add(22);
            integerTree.add(71);
            integerTree.add(54);
            integerTree.add(45);
            integerTree.add(60);
            integerTree.add(31);
            integerTree.add(23);
            integerTree.add(21);
            integerTree.add(33);
            integerTree.delete(24);
            integerTree.delete(19);
            integerTree.delete(17);
            integerTree.delete(20);



//            System.out.println("leftRightNode:");
//            integerTree.goLeftRightNode();
//            System.out.println("leftNodeRight:");
//            integerTree.goLeftNodeRight();
            System.out.println("nodeLeftRight:");
            integerTree.goNodeLeftRight();

//            System.out.println();
//            integerTree.delete(24);
//        Student s1 = new Student("Victoria", 18);
//        Student s2 = new Student("Varya", 16);
//        Student s3 = new Student("Alexander", 43);
//        Student s4 = new Student("Veronica", 41);
//        Student s5 = new Student("Angelina", 18);
//
//        Tree<Student> studentTree = new Tree<>();
//        studentTree.add(s1);
//        studentTree.add(s5);
//        studentTree.add(s2);
//        studentTree.add(s3);
//        studentTree.add(s4);
//        studentTree.leftRightNode();
    }
    }

