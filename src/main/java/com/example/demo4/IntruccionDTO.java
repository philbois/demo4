package com.example.demo4;

import java.util.Optional;

import com.example.demo4.models.Intruccion;
import com.example.demo4.repository.IntruccionRepository;

public class IntruccionDTO {

    private String tipoIntruccion;
    private String descripcion;

    IntruccionRepository intruccionRepository;


    public IntruccionDTO() {
    }
    


    public IntruccionDTO(String tipoIntruccion, String descripcion) {
        this.tipoIntruccion = tipoIntruccion;
        this.descripcion = descripcion;
    }

    public Optional<Intruccion> getIntruccionID(int ID){
        return intruccionRepository.findById(ID);
    }

    public String getTipoIntruccion() {
        return tipoIntruccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
