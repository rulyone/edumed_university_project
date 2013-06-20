/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionespecialistas.controlador;

import cl.natjara.edumed.gestionespecialidades.servicios.GestionEspecialidadesServices;
import cl.natjara.edumed.gestionespecialistas.servicios.GestionEspecialistasServices;
import cl.natjara.edumed.modelo.Especialidad;
import cl.natjara.edumed.modelo.Especialista;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Natjara
 */
@Named(value = "gestionEspecialistasMB")
@ViewScoped
public class GestionEspecialistasMB implements Serializable{

    private @Inject
    GestionEspecialistasServices servicios;
    private @Inject
    GestionEspecialidadesServices serviciosEspecialidades;
    private String rut;
    private String nombre;
    private String telefono;
    private String celular;
    private String email;
    private Date fecha_nacimiento;
    
    private List<Especialista> especialistasActuales;
    private Especialista especialistaSeleccionado;
    
    private DualListModel<Especialidad> especialidades;
    private DualListModel<Especialidad> dualListModel;
 
    public GestionEspecialistasMB() {
    }

    @PostConstruct
    private void init() {
        //INICIALIZAMOS LA LISTA DE Laboratorios.
        System.out.println("PASE POR ACÁ...");
        especialistasActuales = servicios.obtenerEspecialistas();
        List<Especialidad> source = serviciosEspecialidades.obtenerEspecialidades(); //fuente de la DB
        List<Especialidad> target = new ArrayList<Especialidad>(); //destino vacio
        this.especialidades =  new DualListModel<Especialidad>(source, target);
    }

    public String agregarEspecialista() {
        System.out.println("GestionEspecialistasMB.agregarEspecialista()");
        try {
            servicios.agregarEspecialista(rut, nombre, telefono, celular, email, fecha_nacimiento, especialidades.getTarget());
            especialistasActuales = servicios.obtenerEspecialistas();
            this.nombre = null;
            this.rut = null;
            this.telefono = null;
            this.celular = null;
            this.fecha_nacimiento = null;
            this.email = null;
            
            this.especialidades.getTarget().clear();
            this.especialidades.getSource().clear();
            this.especialidades.getSource().addAll(serviciosEspecialidades.obtenerEspecialidades());
//            this.especialidadesSeleccionadas.clear(); //vaciamos las especialidades seleccionadas
            
            Messages.addGlobalInfo("Especialista agregado satisfactoriamente.");
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
            return null; //LO MANTIENE EN LA MISMA PÁGINA
        }
        return null;
    }

    public void seleccionarEspecialista(Especialista especialista) {
        System.out.println("seleccionarEspecialista");
        this.especialistaSeleccionado = especialista;
        //DB - especialista = las que no tiene
      List<Especialidad> source = especialista.getEspecialidades(); //las especialidades que NO tiene el especialista
      List<Especialidad> target = especialista.getEspecialidades(); //las especialidades qu YA tiene e l especialista.
           
      dualListModel = new DualListModel<Especialidad>(source, target);
    }

    public void modificarEspecialista() {
        try {
            servicios.modificarEspecialista(especialistaSeleccionado.getId() ,especialistaSeleccionado.getRutPasaporte(), especialistaSeleccionado.getNombre(), especialistaSeleccionado.getTelefono(), especialistaSeleccionado.getCelular(),especialistaSeleccionado.getEmail(), especialistaSeleccionado.getFechaNacimiento(), dualListModel.getTarget());
            especialistasActuales = servicios.obtenerEspecialistas();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public DualListModel<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(DualListModel<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<Especialista> getEspecialistasActuales() {
        return especialistasActuales;
    }

    public void setEspecialistasActuales(List<Especialista> especialistasActuales) {
        this.especialistasActuales = especialistasActuales;
    }

    public Especialista getEspecialistaSeleccionado() {
        return especialistaSeleccionado;
    }

    public void setEspecialistaSeleccionado(Especialista especialistaSeleccionado) {
        this.especialistaSeleccionado = especialistaSeleccionado;
    }
    
}
