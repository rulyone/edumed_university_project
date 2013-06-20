/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Natjara
 */
@Entity
public class SolicitudLaboratorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_laboratorio")
    private Laboratorio laboratorio;
    @ManyToOne
    @JoinColumn(name = "id_tratamiento")
    private Tratamiento tratamiento;
    private Integer montoLaboratorio;
    private String retira; //quien retira
    private String descripcion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSolicitud;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRecepcion;

    //*****************GETTERS Y SETTERS******************************//
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Integer getMontoLaboratorio() {
        return montoLaboratorio;
    }

    public void setMontoLaboratorio(Integer montoLaboratorio) {
        this.montoLaboratorio = montoLaboratorio;
    }

    public String getRetira() {
        return retira;
    }

    public void setRetira(String retira) {
        this.retira = retira;
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
        if (!(object instanceof SolicitudLaboratorio)) {
            return false;
        }
        SolicitudLaboratorio other = (SolicitudLaboratorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.natjara.edumed.modelo.SolicitudLaboratorio[ id=" + id + " ]";
    }
}
