/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.utils;

import javax.ejb.ApplicationException;

/**
 *
 * @author Natjara
 */
@ApplicationException(rollback = true, inherited = true)
public class ExcepcionLogicaDeNegocio extends Exception {

    public ExcepcionLogicaDeNegocio() {
        super();
    }

    public ExcepcionLogicaDeNegocio(String mensaje) {
        super(mensaje);
    }
}
