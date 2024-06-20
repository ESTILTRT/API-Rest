package med.vol.api.Domain.Consulta.Validaciones.Agender;

import jakarta.validation.ValidationException;
import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamiento implements ValidadorDeConsultasa {

    @Override
    public void validar(DatosAgendarConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura = datos.fecha().getHour() < 7;
        var depuesdeDeCierre = datos.fecha().getHour() > 19;
        if (domingo || antesDeApertura || depuesdeDeCierre){
            throw new ValidationException("El horario de atención de la clínica es de lunes a sábado, de 07:00 " +
                    "a 19:00 horas");
        }
    }
}
