package com.example.demo4.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo4.IntruccionDTO;
@Entity
public class Intruccion {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String tipoIntruccion;
    private String descripcion;



    public Intruccion() {
    }

    public Intruccion(Integer id, String tipoIntruccion, String descripcion) {
        this.id = id;
        this.tipoIntruccion = tipoIntruccion;
        this.descripcion = descripcion;
    }


    public Intruccion(IntruccionDTO intruccionDTO){
       
        this.tipoIntruccion= intruccionDTO.getTipoIntruccion();
        this.descripcion= intruccionDTO.getDescripcion();
    }

    public String getTipoIntruccion() {
        return tipoIntruccion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
