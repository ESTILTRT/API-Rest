package med.vol.api.Domain.Consulta.Validaciones.Agender;

import jakarta.validation.ValidationException;
import med.vol.api.Domain.Consulta.ConsultaRepository;
import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteConConsulta implements ValidadorDeConsultasa{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DatosAgendarConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimaHora = datos.fecha().withHour(18);

        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimaHora);

        if(pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene agendada una cita para ese día...");
        }
    }
}
