/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionpagos.controlador;

import cl.natjara.edumed.gestionpagos.servicios.GestionPagosServices;
import cl.natjara.edumed.modelo.Pago;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.omnifaces.util.Messages;

/**
 *
 * @author Natjara
 */
@Named(value = "gestionPagosMB")
@ViewScoped
public class GestionPagosMB implements Serializable{

   private @Inject
    GestionPagosServices servicios;
    private Date fecha;
    private Integer monto;
    private String descripcion;
    private List<Pago> pagosActuales;
    private Pago pagoSeleccionado;

    public GestionPagosMB() {
    }

    @PostConstruct
    private void init() {
        //INICIALIZAMOS LA LISTA DE Laboratorios.
        System.out.println("PASE POR ACÁ...");
        pagosActuales = servicios.obtenerPagos();
    }

    public String agregarPago() {
//        try {
//            servicios.agregarPago(fecha, monto, descripcion);
//            pagosActuales = servicios.obtenerPagos();
//            this.fecha = null;
//            this.monto = null;
//            this.descripcion = null;
//            Messages.addGlobalInfo("Pago agregado satisfactoriamente.");
//        } catch (ExcepcionLogicaDeNegocio ex) {
//            Messages.addGlobalError(ex.getMessage());
//            return null; //LO MANTIENE EN LA MISMA PÁGINA
//        }
        return null;
    }

    public void seleccionarPago(Pago pago) {
        System.out.println("seleccionarPago");
        this.pagoSeleccionado = pago;
    }

    public void modificarPago() {
        try {
            servicios.modificarPago(pagoSeleccionado.getId(), pagoSeleccionado.getFecha() ,pagoSeleccionado.getMonto(), pagoSeleccionado.getDescripcion());
            Messages.addGlobalInfo("Pago modificado satisfactoriamente.");
            pagosActuales = servicios.obtenerPagos();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
        }
        
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Pago> getPagosActuales() {
        return pagosActuales;
    }

    public void setPagosActuales(List<Pago> pagosActuales) {
        this.pagosActuales = pagosActuales;
    }

    public Pago getPagoSeleccionado() {
        return pagoSeleccionado;
    }

    public void setPagoSeleccionado(Pago pagoSeleccionado) {
        this.pagoSeleccionado = pagoSeleccionado;
    }
}
