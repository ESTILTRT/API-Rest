package med.vol.api.Domain.Medico.DTOs;

import jakarta.validation.constraints.NotNull;
import med.vol.api.Domain.Direccion.DatosDireccion;

public record DatosActualizarMedico(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
