/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student.BUS;

import Student.DTO.StudentDto;
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
    
    public static boolean AddStudent(StudentDto st) {
        try {
            String[] strs = new String[]{st.getCode(), st.getName(), st.getGender(), st.getStClass()};
            return FileHelper.WriteFile(fileStdudent, strs);
        } catch (Exception ex) {
            return false;
        }
    }
}
