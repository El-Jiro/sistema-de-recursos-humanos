package gm.rrhh.controlador;

import gm.rrhh.modelo.Empleado;
import gm.rrhh.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private EmpleadoServicio empleadoServicio;

    /*
    * Creamos el método para recuperar todos los registros de la tabla Empleado
    * y le agregamos la anotación @GetMapping para especificar que se trata de una
    * petición get. Especificamos también la url de la misma*/

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        //Invocamos el método listarEmpleados de la clase de servicio y lo guardamos en empleados
        var empleados = empleadoServicio.listarEmpleados();
        //Imprimimos la lista en consola con un forEach
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        logger.info(nl);
        //devolvemos la lista
        return empleados;
    }

    @PostMapping("/agregar")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar {}", empleado + nl);
        return empleadoServicio.guardarEmpleado(empleado);
    }

}
