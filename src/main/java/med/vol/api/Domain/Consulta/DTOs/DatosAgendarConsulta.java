package med.vol.api.Domain.Consulta.DTOs;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.Domain.Medico.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        @NotNull
        Long idPaciente,
        Long idMedico,
        Especialidad especialidad,
        @NotNull
        @Future
        LocalDateTime fecha
) {
}
