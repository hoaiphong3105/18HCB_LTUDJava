/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student.BUS;

import Student.DTO.StudentDto;
import java.util.ArrayList;
import pkgQLSV.FileHelper;
import pkgQLSV.Main;

/**
 *
 * @author hoaip
 */
public class StudentBUS {

    //static variables
    public static String fileStdudent = "sinhvien.txt";
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
}
