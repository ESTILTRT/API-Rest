package med.vol.api.Domain.Consulta.Validaciones.Agender;

import jakarta.validation.ValidationException;
import med.vol.api.Domain.Consulta.ConsultaRepository;
import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta  implements ValidadorDeConsultasa{
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosAgendarConsulta datos){
        var medicoConConsulta = repository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());

        if(medicoConConsulta){
            throw new ValidationException("Este medico ya tiene una cita agendada para esa hora ");
        }
    }
}
