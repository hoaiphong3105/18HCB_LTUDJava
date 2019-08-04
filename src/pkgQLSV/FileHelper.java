/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgQLSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author hoaip
 */
public class FileHelper {

    public static boolean WriteFile(String filePath, String[] contains) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath, true);
            OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(writer);
            for (int i = 0; i < contains.length; i++) {
                bw.write(contains[i]);

                if (i != contains.length - 1) {
                    bw.write(",");
                }
            }
            bw.newLine();
            bw.close();

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static ArrayList<String[]> ReadFile(String filePath) {
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String line = br.readLine();
            while (line != null) {
                String[] items = line.split(",");
                data.add(items);
                line = br.readLine();
            }
            return data;
        } catch (Exception ex) {
            return null;
        }
    }
}
