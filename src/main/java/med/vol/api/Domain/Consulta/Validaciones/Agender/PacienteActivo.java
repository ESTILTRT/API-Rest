package med.vol.api.Domain.Consulta.Validaciones.Agender;

import jakarta.validation.ValidationException;
import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import med.vol.api.Domain.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultasa{

    @Autowired
    private PacienteRepository repository;

    @Override
    public void validar(DatosAgendarConsulta datos){
        if(datos.idPaciente() == null){
            return;
        }
        var paciente = repository.getReferenceById(datos.idPaciente());

        if (!paciente.getActivo()){
            throw new ValidationException("No se permite agendar citas con pacientes inactivos en el sistema");
        }
    }
}
