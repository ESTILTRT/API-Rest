package med.vol.api.Domain.Consulta.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCancelarConsulta(
        @NotNull
        Long idConsulta,
        @NotBlank
        String motivo
) {
}
