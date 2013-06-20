/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionpacientes.servicios;

import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import cl.natjara.edumed.modelo.Ficha;
import cl.natjara.edumed.modelo.Paciente;
import cl.natjara.edumed.modelo.daos.FichaFacade;
import cl.natjara.edumed.modelo.daos.PacienteFacade;
import java.util.ArrayList;
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
    private @Inject
    FichaFacade fichaFacade;

    /**
    private String rut;
    private String nombre;
    private String direccion;
    private String telefono;
    private String celular;
    private boolean sexo; //V = masculino, F = femeinino
    private String email;
    private Date fechaNacimiento;
    private Ficha ficha;
     */
    public Paciente agregarPacienteNuevo(String rut, String nombre, String direccion, String telefono, String celular, boolean sexo, String email, Date fechaNacimiento) throws ExcepcionLogicaDeNegocio {
        Paciente paciente = pacienteFacade.buscarPacientePorRut(rut);
        if (paciente != null) {
            throw new ExcepcionLogicaDeNegocio("Ya existe un paciente con ese rut.");
        }
        paciente = new Paciente();
        paciente.setRut(rut);
        paciente.setCelular(celular);
        paciente.setDireccion(direccion);
        paciente.setEmail(email);
        paciente.setFechaNacimiento(fechaNacimiento);
//        paciente.setFicha(//);
        paciente.setNombre(nombre);
        paciente.setSexo(sexo);
        paciente.setTelefono(telefono);
        
        Ficha ficha = new Ficha();
        ficha.setCitas(new ArrayList<Cita>());
        ficha.setDescripcion("");
        ficha.setPaciente(paciente);
        
        paciente.setFicha(ficha);
        
        pacienteFacade.create(paciente);
        
        fichaFacade.create(ficha);
                
        return paciente;
        
    }

   public void modificarPaciente(String Rut, String nuevoNombre, String nuevoTelefono, String nuevoCelular, String nuevoDireccion, Date nuevofechaNacimiento, String nuevoEmail) throws ExcepcionLogicaDeNegocio {
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
