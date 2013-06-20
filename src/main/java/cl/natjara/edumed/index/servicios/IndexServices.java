/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.index.servicios;

import cl.natjara.edumed.modelo.Paciente;
import cl.natjara.edumed.modelo.daos.PacienteFacade;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rulyone
 */
@Stateless
public class IndexServices {
    
    private @Inject
    PacienteFacade pacienteFacade;

    public Paciente obtenerPacientePorRut(String rut) {
        return pacienteFacade.buscarPacientePorRut(rut);
    }

}
