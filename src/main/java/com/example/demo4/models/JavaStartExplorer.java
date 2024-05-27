package com.example.demo4.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo4.DTO.JavaStartExplorerDTO;


@Entity
public class JavaStartExplorer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String estadoNave;
    private String ubicacion;
    private String actividad;

  
    public JavaStartExplorer(JavaStartExplorerDTO javaStartExplorerDTO) {

        this.estadoNave = javaStartExplorerDTO.getEstadoNave();
        this.ubicacion = javaStartExplorerDTO.getUbicacion();
        this.actividad =javaStartExplorerDTO.getActividad();
    }

    public JavaStartExplorer(Integer id, String estadoNave, String ubicacion, String actividad) {
        this.id = id;
        this.estadoNave = estadoNave;
        this.ubicacion = ubicacion;
        this.actividad = actividad;
    }


    public String getEstadoNave() {
        return estadoNave;
    }


    public String getUbicacion() {
        return ubicacion;
    }


    public String getActividad() {
        return actividad;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEstadoNave(String estadoNave) {
        this.estadoNave = estadoNave;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }




  

}
