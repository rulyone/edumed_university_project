/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionpagos.servicios;

import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.modelo.Pago;
import cl.natjara.edumed.modelo.daos.CitaFacade;
import cl.natjara.edumed.modelo.daos.PagoFacade;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
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
    private @Inject
    CitaFacade citaFacade;

    public void agregarPago(Date fecha, Integer monto, String descripcion, Cita cita) throws ExcepcionLogicaDeNegocio {
        if (monto == null) {
            throw new ExcepcionLogicaDeNegocio("El monto es obligatorio.");
        }
        if (cita == null) {
            throw new ExcepcionLogicaDeNegocio("Cita no existe.");
        }
        citaFacade.edit(cita); //attach....
        
        
        Pago pago = new Pago();
        pago.setFecha(fecha);
        pago.setMonto(monto);
        pago.setDescripcion(descripcion);
        pago.setCita(cita);


        pagoFacade.create(pago);
        
        //la reversa
        cita.setPago(pago);
        citaFacade.edit(cita);
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
