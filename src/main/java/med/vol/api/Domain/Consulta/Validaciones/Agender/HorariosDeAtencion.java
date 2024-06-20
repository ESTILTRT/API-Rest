package med.vol.api.Domain.Consulta.Validaciones.Agender;

import jakarta.validation.ValidationException;
import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorariosDeAtencion implements ValidadorDeConsultasa{
    @Override
    public void validar(DatosAgendarConsulta datos){
        var ahora = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();

        var diferenciaDe30Min = Duration.between(ahora,horaDeConsulta).toMinutes() < 30;

        if (diferenciaDe30Min){
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipación ");
        }
    }
}
