/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.Exception.Exceptions;

/**
 *
 * @author Chahir Chalouati
 */
public class MedicineNotFoundException extends RuntimeException {

    public MedicineNotFoundException(String string) {
        super(string);
    }

}
