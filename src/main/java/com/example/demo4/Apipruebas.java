package com.example.demo4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import com.example.demo4.DTO.JavaStartExplorerDTO;
import com.example.demo4.models.JavaStartExplorer;
import com.example.demo4.services.JavaStartExplorerServices;
import com.example.demo4.services.JavaStartExplorerServicesImpl;

@RestController
@RequestMapping (value ="/api")

public class Apipruebas {

     @Autowired
    JavaStartExplorerServices javaStartExplorerServices;

    @GetMapping(value = "/prueba")
        public String algo(){
            return "hola que tal";
        }
    @PostMapping(value = "/intruccion")
    @ResponseBody
    public ResponseEntity<Map<String,String>> intruccion (@RequestBody IntruccionDTO intruccionDTO){
        Object Resultado= null;
        try {
            switch (intruccionDTO.getComando()) {
                case "Scan":
                Set<JavaStartExplorerDTO> lista=  new HashSet <JavaStartExplorerDTO> ();
                JavaStartExplorerDTO suelo1 = new JavaStartExplorerDTO();
                
       
                Resultado= "Scaneo";
                    break;
                    case "Deploy Rover":

                    break;
            
                default:
                    break;
            }
            Map response = new HashMap();
            response.put("status","OK");
            response.put("code","200");
            response.put("message", Resultado);
            return new ResponseEntity<>(response, HttpStatus.OK);
         }
        catch (Exception e) {
            return null;
        }
    }

  
    @GetMapping(value = "/telemetry")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> telemetry(){
        try {
            Set<JavaStartExplorerDTO> lista=  new HashSet <JavaStartExplorerDTO> ();
            JavaStartExplorerDTO nave = new JavaStartExplorerDTO();
          //  nave.setMuestraSuelo("rock");
            lista.add(nave);
            Map<String,Object> response = new HashMap<String,Object>();
            response.put("status","OK");
            response.put("code","200");
            response.put("message", lista);
            return new ResponseEntity<>(response, HttpStatus.OK);
         }
        catch (Exception e) {
            return null;
        }

    }

    @PostMapping(value = "/telemetry2")
    @ResponseBody
    public ResponseEntity<Map<String, String>> telemetry2(@RequestBody JavaStartExplorerDTO javaStartExplorerDTO) {

        try {

            JavaStartExplorer datos = new JavaStartExplorer(javaStartExplorerDTO);
            javaStartExplorerServices.enviarDatos(datos);

            Map response = new HashMap();
            response.put("status","OK");
            response.put("code","200");
            response.put("message", "exito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (HttpMessageNotReadableException e) {
            return handleBadRequestException(e);
        }
        catch (DataIntegrityViolationException e){
            return handleInternalServerErrorUnique(e, "--");
        }
        catch (Exception e) {
            return handleInternalServerError(e);
        }
    }

      //Excepciones en tiempo de serialización para web services
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleBadRequestException(HttpMessageNotReadableException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "BAD_REQUEST");
        response.put("code", "400");
        response.put("message", "Revise los campos de la solicitud: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleInternalServerErrorUnique(Exception ex, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "INTERNAL_SERVER_ERROR");
        response.put("code", "500");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleInternalServerError(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "INTERNAL_SERVER_ERROR");
        response.put("code", "500");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
