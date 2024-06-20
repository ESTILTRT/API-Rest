package med.vol.api.Domain.Paciente;

import med.vol.api.Domain.Paciente.DTOs.DatosListadoPacientes;
import med.vol.api.Domain.Paciente.DTOs.DatosRespuestaPaciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByActivoTrue(Pageable paginacion);
}
