package med.vol.api.Domain.Consulta;

import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import med.vol.api.Domain.Consulta.DTOs.DatosDetalleConsulta;
import med.vol.api.Domain.Consulta.Validaciones.Agender.ValidadorDeConsultasa;
import med.vol.api.Domain.Medico.Medico;
import med.vol.api.Domain.Medico.MedicoRepository;
import med.vol.api.Domain.Paciente.PacienteRepository;
import med.vol.api.Infra.Erros.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    List<ValidadorDeConsultasa> validadores;

    public DatosDetalleConsulta agender(DatosAgendarConsulta datos){
        if (!pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Este id de paciente no fue encontrado");
        }
        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Este id de medico no fue encontrado");
        }

        validadores.forEach(v -> v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);
        if (medico == null){
            throw new ValidacionDeIntegridad("No hay médico disponible para esta fecha...");
        }
        Consulta consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null){
            throw new ValidacionDeIntegridad("Se debe seleccionar una especialidad del dector");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }
}
