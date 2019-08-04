/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student.BUS;

import static Student.BUS.StudentBUS.fileStdudent;
import Student.DTO.StudentDto;
import Student.DTO.TkbDto;
import java.util.ArrayList;
import pkgQLSV.FileHelper;

/**
 *
 * @author hoaip
 */
public class TkbBUS {

    //static variables
    public static String fileStdudent = "tkb.txt";
    public static String fileStdudent_tkb = "st_tkb.txt";
    //

    public static boolean addTKb(TkbDto tkb) {
        try {
            String[] strs = new String[]{tkb.getCode(), tkb.getName(), tkb.getRoom(), tkb.getStClass()};
            return FileHelper.WriteFile(fileStdudent, strs);
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean addStudent_Tkb(TkbDto tkb) {
        try {
            ArrayList<StudentDto> students = StudentBUS.getAllStudentByClass(tkb.getStClass());
            if (students != null) {
                for (int i = 0; i < students.size(); i++) {
                    StudentDto st = students.get(i);
                    String[] strs = new String[]{tkb.getCode(), st.getStClass(), st.getCode()};
                    FileHelper.WriteFile(fileStdudent_tkb, strs);
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     *
     * @param path
     * @return
     */
    public static ArrayList<TkbDto> getAllTkb() {
        ArrayList<TkbDto> list = new ArrayList<TkbDto>();
        try {
            ArrayList<String[]> lines = FileHelper.ReadFile(fileStdudent);
            if (lines != null) {
                for (int i = 0; i < lines.size(); i++) {
                    TkbDto tkb = new TkbDto();
                    String[] strs = lines.get(i);
                    tkb.setCode(strs[0]);
                    tkb.setName(strs[1]);
                    tkb.setRoom(strs[2]);

                    list.add(tkb);
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return list;
    }

    public static ArrayList<TkbDto> getAllTKbByClass(String stClass) {
        try {
            ArrayList<TkbDto> list = new ArrayList<TkbDto>();
            ArrayList<TkbDto> res = new ArrayList<TkbDto>();
            list = getAllTkb();
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
}
