/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Natjara
 */
@Entity
public class Tratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)//VALIDACION A NIVEL DE DB
    @NotEmpty //VALIDACION A NIVEL DE JAVA
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "tratamiento")
    private List<SolicitudLaboratorio> solicitudes;
    @OneToMany(mappedBy = "tratamiento")
    private List<SolicitudTratamiento> solicitudesTratamiento;

    //GETTERS Y SETTERS//
    public List<SolicitudTratamiento> getSolicitudesTratamiento() {
        return solicitudesTratamiento;
    }

    public void setSolicitudesTratamiento(List<SolicitudTratamiento> solicitudesTratamiento) {
        this.solicitudesTratamiento = solicitudesTratamiento;
    }

    public List<SolicitudLaboratorio> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudLaboratorio> solicitudes) {
        this.solicitudes = solicitudes;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tratamiento)) {
            return false;
        }
        Tratamiento other = (Tratamiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.natjara.edumed.modelo.Tratamiento[ id=" + id + " ]";
    }
}
