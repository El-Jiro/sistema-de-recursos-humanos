package gm.rrhh.controlador;

import gm.rrhh.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Definimos el dominio base de la aplicación
@RequestMapping("rrhh-app")
//Habilitamos el CORS con React
@CrossOrigin("http://localhost:3000")
public class EmpleadoControlador {

    //Creamos una instancia de logger para mandar información a la consola
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);
    //Creamos un salto de línea
    private static final String nl = System.lineSeparator();
    //Inyectamos una instancia de la clase de serivici
    @Autowired
    private static EmpleadoServicio empleadoServicio;

}
