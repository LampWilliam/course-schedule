package com.courseschedule;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public static class Student {
        @Getter
        @Setter
        private String studentNo;
        @Getter
        @Setter
        private String name;
        @Getter
        @Setter
        private String gender;
        @Getter
        @Setter
        private String birthday;
        @Getter
        @Setter
        private String number;
        @Getter
        @Setter
        private String classNo;
        @Getter
        @Setter
        private String password;

        public Student(String studentNo, String name, String gender, String birthday, String number, String classNo, String password) {
            this.studentNo = studentNo;
            this.name = name;
            this.gender = gender;
            this.birthday = birthday;
            this.number = number;
            this.classNo = classNo;
            this.password = password;
        }
        @Override
        public String toString() {
            return studentNo + " " + name + " " + gender + " " + birthday + " " + number + " " + classNo + " " + password;
        }

        // Getters and setters

    }

    public void loadStudentsFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\s+");
                if (data.length == 7) {
                    students.add(new Student(data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
                }
            }
        }
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile("C:\\Users\\20416\\Desktop\\CourseSchecule\\students.txt");
    }

    public void deleteStudent(String studentNo) {
        students.removeIf(s -> s.getStudentNo().equals(studentNo));
        saveStudentsToFile("C:\\Users\\20416\\Desktop\\CourseSchecule\\students.txt");

    }

    public void updateStudent(String studentNo, Student newStudentData) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentNo().equals(studentNo)) {
                students.set(i, newStudentData);
                break;
            }
        }
        saveStudentsToFile("C:\\Users\\20416\\Desktop\\CourseSchecule\\students.txt");
    }

    public Student queryStudent(String studentNo) {
        for (Student s : students) {
            if (s.getStudentNo().equals(studentNo)) {
                return s;
            }
        }
        return null;
    }

    public void Show(){
        for (Student s : students) {
            System.out.print(s.getStudentNo()+" ");
            System.out.print(s.getName()+" ");
            System.out.println(s.getGender());
        }
    }

    public void saveStudentsToFile(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Student s : students) {
                pw.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        try {
            manager.loadStudentsFromFile("C:\\Users\\20416\\Desktop\\CourseSchecule\\students.txt");
            // 示例：添加学生
            manager.addStudent(new Student("37", "新学生", "男", "2001-01-01", "66666", "00000001", "1234"));
            // 示例：删除学生
            manager.deleteStudent("1");
            // 示例：更新学生信息
            manager.updateStudent("37", new Student("37", "更新后的名字", "女", "2002-01-02", "77777", "00000002", "5678"));
            // 示例：查询学生信息
            Student queriedStudent = manager.queryStudent("3");
            if (queriedStudent != null) {
                System.out.println(queriedStudent.getGender());
            }
            manager.Show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
