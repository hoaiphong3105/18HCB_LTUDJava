/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student.BUS;

import static Student.BUS.StudentBUS.fileStdudent;
import Student.DTO.TkbDto;
import pkgQLSV.FileHelper;

/**
 *
 * @author hoaip
 */
public class TkbBUS {
    //static variables
    public static String fileStdudent = "tkb.txt";
    //
    
    public static boolean addTKb(TkbDto tkb) {
        try {
            String[] strs = new String[]{tkb.getCode(), tkb.getName(), tkb.getRoom()};
            return FileHelper.WriteFile(fileStdudent, strs);
        } catch (Exception ex) {
            return false;
        }
    }
}
