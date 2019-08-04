/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student.BUS;

import Student.DTO.StudentDto;
import Student.DTO.Student_CourseDto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import pkgQLSV.FileHelper;
import pkgQLSV.Main;

/**
 *
 * @author hoaip
 */
public class StudentBUS {

    //static variables
    public static String fileStdudent = "sinhvien.txt";
    public static String fileStdudent_tkb = "st_tkb.txt";
    //

    public static boolean addStudent(StudentDto st) {
        try {
            String[] strs = new String[]{st.getCode(), st.getName(), st.getGender(), st.getStClass()};
            return FileHelper.WriteFile(fileStdudent, strs);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     *
     * @param path
     * @return
     */
    public static ArrayList<StudentDto> getAllStudents(String path) {
        if (path.equals("")) {
            path = fileStdudent;
        }
        ArrayList<StudentDto> list = new ArrayList<StudentDto>();
        try {
            ArrayList<String[]> lines = FileHelper.ReadFile(path);
            if (lines != null) {
                for (int i = 0; i < lines.size(); i++) {
                    StudentDto st = new StudentDto();
                    String[] strs = lines.get(i);
                    st.setCode(strs[0]);
                    st.setName(strs[1]);
                    st.setGender(strs[2]);
                    st.setStClass(strs[3]);

                    list.add(st);
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return list;
    }

    public static ArrayList<StudentDto> getAllStudentByClass(String stClass) {
        try {
            ArrayList<StudentDto> list = new ArrayList<StudentDto>();
            ArrayList<StudentDto> res = new ArrayList<StudentDto>();
            list = getAllStudents("");
            if (list == null) {
                return null;
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getStClass().equals(stClass)) {
                    res.add(list.get(i));
                }
            }
            return res;
        } catch (Exception ex) {
            return null;
        }
    }

    public static ArrayList<Student_CourseDto> getAllStudents_Courses() {
        ArrayList<Student_CourseDto> list = new ArrayList<Student_CourseDto>();
        try {
            ArrayList<String[]> lines = FileHelper.ReadFile(fileStdudent_tkb);
            if (lines != null) {
                for (int i = 0; i < lines.size(); i++) {
                    Student_CourseDto st = new Student_CourseDto();
                    String[] strs = lines.get(i);
                    st.setCourse(strs[0]);
                    st.setStClass(strs[1]);
                    st.setStCode(strs[2]);

                    list.add(st);
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return list;
    }

    public static ArrayList<StudentDto> getAllStudentByClassAndCourse(String stClass, String course) {
        try {
            ArrayList<StudentDto> list = new ArrayList<StudentDto>();
            ArrayList<Student_CourseDto> list2 = new ArrayList<Student_CourseDto>();
            ArrayList<StudentDto> res = new ArrayList<StudentDto>();

            list = getAllStudents("");
            list2 = getAllStudents_Courses();
            if (list == null || list2 == null) {
                return null;
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (list2.get(j).getStClass().equals(stClass)
                            && list2.get(j).getCourse().equals(course)
                            && list2.get(j).getStCode().equals(list.get(i).getCode())) {
                        res.add(list.get(i));
                    }
                }
            }
            return res;
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean addStudent_Courses(Student_CourseDto st) {
        try {
            String[] strs = new String[]{st.getCourse(), st.getStClass(), st.getStCode()};
            return FileHelper.WriteFile(fileStdudent_tkb, strs);
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean removeStudent_Courses(String course, String StCode, String stClass) throws FileNotFoundException {
        ArrayList<String> tempArray = new ArrayList<>();
        FileInputStream fileStream = new FileInputStream(fileStdudent_tkb);

        try (InputStreamReader reader = new InputStreamReader(fileStream, StandardCharsets.UTF_8);) {
            Scanner x = new Scanner(new File(fileStdudent_tkb), "UTF-8");
            String line;
            String[] lineArr;

            while (x.hasNext()) {
                x.useDelimiter("[\r\n]");
                line = x.next();

                lineArr = line.split(",");
                if (lineArr[0].equals(course)
                        && lineArr[1].equals(stClass)
                        && lineArr[2].equals(StCode)) {
                } else {
                    tempArray.add(
                            lineArr[0] + ","
                            + lineArr[1] + ","
                            + lineArr[2]);
                }
            }
            reader.close();
        } catch (Exception e) {
        }

        FileOutputStream fos = new FileOutputStream(fileStdudent_tkb, false);
        OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(writer);

        try {
            for (String str : tempArray) {
                bw.write(str);
                bw.write("\r\n");
            }
            bw.close();

            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
