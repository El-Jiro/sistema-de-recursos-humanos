package gm.rrhh.excepcion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@NoArgsConstructor
public class NotFoundException extends RuntimeException{

    private String mensaje;
    private final int statusCode = 404;

    public NotFoundException(String mensaje){
        super(mensaje);
        this.mensaje = mensaje;
    }
}
