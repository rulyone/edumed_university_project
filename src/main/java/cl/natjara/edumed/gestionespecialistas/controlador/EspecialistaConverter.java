/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionespecialistas.controlador;

import cl.natjara.edumed.gestionespecialistas.servicios.GestionEspecialistasServices;
import cl.natjara.edumed.modelo.Especialista;
import cl.natjara.edumed.utils.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rulyone
 */
@FacesConverter("especialistaConverter")
public class EspecialistaConverter implements Converter {
    
    private  GestionEspecialistasServices services = EJB.lookup(GestionEspecialistasServices.class) ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //Convertimos de String  a Especialista
        Long idEspecialista = Long.parseLong(value);
        Especialista esp = services.buscarEspecialistaPorId(idEspecialista);
        return esp;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //Convertimos de Especialista a String
        Especialista esp = (Especialista) value;
        if (esp == null) {
            return null;
        }
        return String.valueOf(esp.getId());
        
    }
}
