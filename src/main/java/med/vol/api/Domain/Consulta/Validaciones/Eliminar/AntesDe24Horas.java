package med.vol.api.Domain.Consulta.Validaciones.Eliminar;

import jakarta.validation.ValidationException;
import med.vol.api.Domain.Consulta.ConsultaRepository;
import med.vol.api.Domain.Consulta.DTOs.DatosCancelarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AntesDe24Horas implements ValidarEliminarConsulta {
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelarConsulta datos) {
        var consulta = repository.getReferenceById(datos.idConsulta());
        var fecha = consulta.getFecha();
        var ahora = LocalDateTime.now();

        var unDiaAntes = Duration.between(ahora,fecha).toHours() < 24;

        if (unDiaAntes){
            throw new ValidationException("La cita solo se puede cancelar con 24 horas de anticipacion...");
        }
    }
}
