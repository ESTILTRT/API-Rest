package med.vol.api.Domain.Medico.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.vol.api.Domain.Direccion.DatosDireccion;
import med.vol.api.Domain.Medico.Especialidad;

public record DatosRegistroMedico(


        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion
) {
}
