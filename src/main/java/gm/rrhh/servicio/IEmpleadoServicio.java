package gm.rrhh.servicio;

import gm.rrhh.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {

    List<Empleado> listarEmpleados();

    Empleado buscarEmpleadoPorId(int idEmpleado);

    Empleado guardarEmpleado(Empleado empleado);

    void borrarEmpleado(int idEmpleado);
}
