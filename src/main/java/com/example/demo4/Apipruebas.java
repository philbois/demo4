package com.example.demo4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
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
import com.example.demo4.models.Intruccion;
import com.example.demo4.models.JavaStartExplorer;
import com.example.demo4.services.IntruccionServices;
import com.example.demo4.services.JavaStartExplorerServices;
import com.example.demo4.services.JavaStartExplorerServicesImpl;

@RestController
@RequestMapping (value ="/api")

public class Apipruebas {

     @Autowired
    JavaStartExplorerServices javaStartExplorerServices;

    @Autowired
    IntruccionServices intruccionServices;

    @GetMapping(value = "/prueba")
        public String algo(){
            return "Rover desplegado. terreno arido";
        }

        //como hacer la parte de recibir intruccion con get
    @PostMapping(value = "/intruccion")
    @ResponseBody
    public ResponseEntity<Map<String,String>> intruccion (@RequestBody IntruccionDTO intruccionDTO){
        Object Resultado= null;
        try {
            switch (intruccionDTO.getTipoIntruccion()) {
                case "Scan":
                Resultado= "Realice un escaneo del terreno y envíe los datos de regreso";
                    break;
                    case "Collect Sample":
                    Resultado= "Recojer muestras del suelo y envíe de vuelta a la Tierra";
                    break;
                    case "Deploy Rover":

                    Resultado= "Despliegue un rover en la superficie para explorar áreas específicas";
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

    //aca se envian datos y se almacenan en la base de datos.
   @PostMapping(value = "/intruccion2")
    @ResponseBody
    public ResponseEntity<Map<String,String>> intruccion2 (@RequestBody IntruccionDTO intruccionDTO){
        try {
            Intruccion intruccion = new Intruccion(intruccionDTO);
            intruccionServices.enviarDatos(intruccion);


            Map response = new HashMap();
            response.put("descripcion" , intruccion.getDescripcion());
            response.put("status","OK");
            response.put("code","200");
            response.put("message", " enviado con exito");
            return new ResponseEntity<>(response, HttpStatus.OK);
         }
        catch (Exception e) {
            return handleInternalServerError(e);
        }
    }

   @GetMapping(value = "/intruccion2/{id}")
   @ResponseBody
   public ResponseEntity<Map<String,String>> intruccion2 (int id){
    try {
        IntruccionDTO intruccion = new IntruccionDTO();
        intruccion.getIntruccionID(id);

        Map response = new HashMap();
        
        response.put("status","OK");
        response.put("code","200");
        response.put("descripcion" , intruccion.getDescripcion());
        response.put("message", " enviado con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
     }
    catch (Exception e) {
        return handleInternalServerError(e);
    }
   }

    @PostMapping(value = "/telemetry")
    @ResponseBody
    public ResponseEntity<Map<String, String>> telemetry(@RequestBody JavaStartExplorerDTO javaStartExplorerDTO) {
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
    
  /*  @GetMapping(value = "/telemetry2")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> telemetry2(){
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
 */
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
