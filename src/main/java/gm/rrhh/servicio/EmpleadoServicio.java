package gm.rrhh.servicio;

import gm.rrhh.modelo.Empleado;
import gm.rrhh.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServicio implements IEmpleadoServicio {

    @Autowired
    private static EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listarEmpleados() {
        var empleados = empleadoRepositorio.findAll();
        return empleados;
    }

    @Override
    public Empleado buscarEmpleadoPorId(int idEmpleado) {
        var empleado = empleadoRepositorio.findById(idEmpleado).orElse(null);
        return empleado;
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public void borrarEmpleado(int idEmpleado) {
        empleadoRepositorio.deleteById(idEmpleado);
    }
}
