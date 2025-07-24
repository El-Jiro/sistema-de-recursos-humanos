package gm.rrhh.modelo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Departamento {

    RRHH("Recursos Humanos"),
    TI("Tecnologías de la Información (TI/IT)"),
    FINANZAS("Finanzas"),
    ADMIN("Administración General"),
    SOPORTE_TEC("Soporte Técnico"),
    VENTAS("Ventas"),
    CALIDAD("Control de calidad"),
    MANTENIMIENTO("Mantenimiento"),
    MARKETING("Marketing"),
    LOGISTICA("Logística"),
    PROD("Producción/Operaciones");

    @JsonValue
    private final String displayName;
}
