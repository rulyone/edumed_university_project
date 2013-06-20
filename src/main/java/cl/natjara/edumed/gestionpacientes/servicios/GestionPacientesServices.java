/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionpacientes.servicios;

import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import cl.natjara.edumed.modelo.Ficha;
import cl.natjara.edumed.modelo.Paciente;
import cl.natjara.edumed.modelo.daos.FichaFacade;
import cl.natjara.edumed.modelo.daos.PacienteFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *oli, voz pa q me digai en q ta la app
 * @author Natjara
 */
@Stateless
public class GestionPacientesServices {

    private @Inject
    PacienteFacade pacienteFacade;


   public void modificarLaboratorio(String Rut, String nuevoNombre, String nuevoTelefono, String nuevoCelular, String nuevoDireccion, Date nuevofechaNacimiento, String nuevoEmail) throws ExcepcionLogicaDeNegocio {
        Paciente paciente = pacienteFacade.find(Rut);
        if (paciente == null) {
            //no existe. 
            throw new ExcepcionLogicaDeNegocio("No existe un paciente con esa ID.");
        }
        
        paciente.setNombre(nuevoNombre);
        paciente.setTelefono(nuevoTelefono);
        paciente.setDireccion(nuevoDireccion);
        paciente.setCelular(nuevoCelular);
        paciente.setEmail(nuevoEmail);
        paciente.setFechaNacimiento(nuevofechaNacimiento);

        pacienteFacade.edit(paciente);


    }
    
    public List<Paciente> obtenerPacientes() {
        return pacienteFacade.findAll();
    }
}
