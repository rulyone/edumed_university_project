/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionespecialidades.controlador;

import cl.natjara.edumed.gestionespecialidades.servicios.GestionEspecialidadesServices;
import cl.natjara.edumed.modelo.Especialidad;
import cl.natjara.edumed.modelo.Especialista;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
/**
 *
 * @author Natjara
 */
@Named(value = "gestionEspecialidadesMB")
@ViewScoped
public class GestionEspecialidadesMB implements Serializable{

    private @Inject
    GestionEspecialidadesServices servicios;
    private String nombre;
    private String descripcion;
    private List<Especialista> especialistas;
    
    private List<Especialidad> especialidadesActuales;
    
    private Especialidad especialidadSeleccionado;
    
    public GestionEspecialidadesMB() {
    }
     @PostConstruct
    private void init() {
        //INICIALIZAMOS LA LISTA DE especialidades.
        System.out.println("PASE POR ACÁ...");
        especialidadesActuales = servicios.obtenerEspecialidades();
    }
         public String agregarEspecialidad() {
        try {
            servicios.agregarEspecialidad(nombre, descripcion);
            especialidadesActuales = servicios.obtenerEspecialidades();
            this.nombre=  null;
            this.descripcion = null;
            Messages.addGlobalInfo("Especialidad agregado satisfactoriamente.");
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
            return null; //LO MANTIENE EN LA MISMA PÁGINA
        }
        return null;
    }
    
    public void seleccionarEspecialidad(Especialidad especialidad) {
        System.out.println("seleccionarEspecialidad");
        this.especialidadSeleccionado = especialidad;
          }
    
    public void modificarEspecialidad() {
        try {
            servicios.modificarEspecialidad(especialidadSeleccionado.getNombre(), especialidadSeleccionado.getDescripcion());
            Messages.addGlobalInfo("Tratamiento modificado satisfactoriamente.");
            especialidadesActuales = servicios.obtenerEspecialidades();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Especialista> getEspecialistas() {
        return especialistas;
    }

    public void setEspecialistas(List<Especialista> especialistas) {
        this.especialistas = especialistas;
    }

    public List<Especialidad> getEspecialidadesActuales() {
        return especialidadesActuales;
    }

    public void setEspecialidadesActuales(List<Especialidad> especialidadesActuales) {
        this.especialidadesActuales = especialidadesActuales;
    }

    public Especialidad getEspecialidadSeleccionado() {
        return especialidadSeleccionado;
    }

    public void setEspecialidadSeleccionado(Especialidad especialidadSeleccionado) {
        this.especialidadSeleccionado = especialidadSeleccionado;
    }


}

