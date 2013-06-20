/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionpagos.servicios;


import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.modelo.Pago;
import cl.natjara.edumed.modelo.daos.PagoFacade;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Natjara
 */
@Stateless
public class GestionPagosServices {

    private @Inject
    PagoFacade pagoFacade;
   
    
    public void agregarPago (Date fecha, Integer monto, String descripcion) throws ExcepcionLogicaDeNegocio{
        if (monto == null) {
            throw new ExcepcionLogicaDeNegocio("El monto es obligatorio.");
    }
        Pago pago =new Pago();
        pago.setFecha(fecha);
        pago.setMonto(monto);
        pago.setDescripcion(descripcion);
      
       
        
        pagoFacade.create(pago);
    }
    public void modificarPago(Long id, Date fechaNueva, Integer montoNuevo, String descripcionNueva) throws ExcepcionLogicaDeNegocio {
        Pago pago = pagoFacade.find(id);
        if (pago == null) {
            //no existe. 
            throw new ExcepcionLogicaDeNegocio("No existe un pago con esa ID.");
        }
        
        pago.setFecha(fechaNueva);
        pago.setMonto(montoNuevo);
        pago.setDescripcion(descripcionNueva);
 

        pagoFacade.edit(pago);
    }
    
    public List<Pago> obtenerPagos() {
        return pagoFacade.findAll();
    }
   }
 


