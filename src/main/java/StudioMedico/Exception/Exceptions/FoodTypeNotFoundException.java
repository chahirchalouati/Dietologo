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
public class FoodTypeNotFoundException extends RuntimeException{

    public FoodTypeNotFoundException() {
    }

    public FoodTypeNotFoundException(String string) {
        super(string);
    }

    public FoodTypeNotFoundException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public FoodTypeNotFoundException(Throwable thrwbl) {
        super(thrwbl);
    }

    public FoodTypeNotFoundException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
