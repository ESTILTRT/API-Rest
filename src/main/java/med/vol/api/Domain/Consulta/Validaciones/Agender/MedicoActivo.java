package med.vol.api.Domain.Consulta.Validaciones.Agender;

import jakarta.validation.ValidationException;
import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import med.vol.api.Domain.Medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultasa{
    @Autowired
    private MedicoRepository repository;

    @Override
    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null){
            return;
        }
        var medico = repository.getReferenceById(datos.idMedico());

        if (!medico.getActivo()){
            throw new ValidationException("No se permite agendar citas con medicos inactivos en el sistema");
        }
    }
}
