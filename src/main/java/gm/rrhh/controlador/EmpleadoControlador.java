package gm.rrhh.controlador;

import gm.rrhh.excepcion.NotFoundException;
import gm.rrhh.modelo.Empleado;
import gm.rrhh.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/empleado/{id}")
    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", new NotFoundException("No se encontró un empleado con el id: " + id).getMensaje());
            respuesta.put("código_http", new NotFoundException().getStatusCode());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        } else  {
            return ResponseEntity.ok(empleado);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado){

        //Recuperamos el empleado de la base de datos mediante el id y lo guardamos en empleadoModificado
        var empleadoModificado = empleadoServicio.buscarEmpleadoPorId(id);

        //Si el objeto es nulo, enviamos un 404
        if(empleadoModificado == null){
           Map<String, Object> respuesta = new HashMap<>();
           respuesta.put("mensaje", new NotFoundException("No se encontró un empleado con el id: " + id).getMensaje());
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        } else {
            //De lo contrario, reemplazamos la información del empleado con la enviada en el formulario
            logger.info("Empleado a modificar: {}", empleadoModificado);
            empleadoModificado.setNombre(empleado.getNombre());
            empleadoModificado.setApellido(empleado.getApellido());
            empleadoModificado.setDepartamento(empleado.getDepartamento());
            empleadoModificado.setSueldo(empleado.getSueldo());

            logger.info("Empleado actualizado: {}", empleadoModificado);
            //Guardamos el nuevo objeto en la base de datos
            empleadoServicio.guardarEmpleado(empleadoModificado);

            return ResponseEntity.ok(empleadoModificado);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer id){

        //Comprobamos que exista el producto en la base de datos
        var empleado = this.empleadoServicio.buscarEmpleadoPorId(id);
        //SI
        if (empleado == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).
                   body(new NotFoundException("No existe un empleado con el id: "+ id).getMessage());

        } else {
            //Eliminamos el objeto cuyo id coincida con el que hemos recibido en la url
            this.empleadoServicio.borrarEmpleado(id);

            //Creamos un HashMap para enviarlo como JSON en la respuesta
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje","Se ha eliminado correctamente el empleado con el id: "+ id);

            //Enviamos el hashmap en el cuerpo del ResponseEntity
            return ResponseEntity.ok(respuesta);
        }
    }

}
