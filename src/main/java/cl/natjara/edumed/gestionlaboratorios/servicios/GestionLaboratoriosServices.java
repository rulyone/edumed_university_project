/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionlaboratorios.servicios;

import cl.natjara.edumed.modelo.Laboratorio;
import cl.natjara.edumed.modelo.SolicitudLaboratorio;
import cl.natjara.edumed.modelo.daos.LaboratorioFacade;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author Natjara
 */
@Stateless
public class GestionLaboratoriosServices {

    private @Inject
    LaboratorioFacade laboratorioFacade;
    
    public void agregarLaboratorio (String rut, String nombre, String telefono, String direccion, String representante, String email) throws ExcepcionLogicaDeNegocio{
        if (rut == null || rut.isEmpty()) {
            throw new ExcepcionLogicaDeNegocio("El rut es obligatorio.");
    }
        Laboratorio laboratorio =new Laboratorio();
        laboratorio.setNombre(nombre);
        laboratorio.setTelefono(telefono);
        laboratorio.setDireccion(direccion);
        laboratorio.setRepresentante(representante);
        laboratorio.setEmail(email);
        laboratorio.setRutLaboratorio(rut);
        List<SolicitudLaboratorio> solicitudes = new ArrayList<SolicitudLaboratorio>();
        laboratorio.setSolicitudes(solicitudes);
        
        laboratorioFacade.create(laboratorio);
    }
    public void modificarLaboratorio(Long idLaboratorio, String nuevoRut, String nuevoNombre, String nuevoTelefono, String nuevoDireccion, String nuevoRepresentante, String nuevoEmail) throws ExcepcionLogicaDeNegocio {
        Laboratorio laboratorio = laboratorioFacade.find(idLaboratorio);
        if (laboratorio == null) {
            //no existe. 
            throw new ExcepcionLogicaDeNegocio("No existe un laboratorio con esa ID.");
        }
        
        laboratorio.setRutLaboratorio(nuevoRut);
        laboratorio.setNombre(nuevoNombre);
        laboratorio.setTelefono(nuevoTelefono);
        laboratorio.setDireccion(nuevoDireccion);
        laboratorio.setRepresentante(nuevoRepresentante);
        laboratorio.setEmail(nuevoEmail);
        

        laboratorioFacade.edit(laboratorio);


    }
    
    public List<Laboratorio> obtenerLaboratorios() {
        return laboratorioFacade.findAll();
    }
}
