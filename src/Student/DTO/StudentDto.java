/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student.DTO;

/**
 *
 * @author hoaip
 */
public class StudentDto{
    private String Code;
    private String Name;
    private String StClass;
    private String Gender;

    public String getStClass() {
        return StClass;
    }

    public void setStClass(String StClass) {
        this.StClass = StClass;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
}
