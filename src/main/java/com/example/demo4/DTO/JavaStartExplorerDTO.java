package com.example.demo4.DTO;

import java.util.Random;

public class JavaStartExplorerDTO {
    
    private String estadoNave;
    private String ubicacion;
    private String actividad;



    public JavaStartExplorerDTO() {
    }

    




    public JavaStartExplorerDTO(String estadoNave, String ubicacion, String actividad) {
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
    
  
   

}
