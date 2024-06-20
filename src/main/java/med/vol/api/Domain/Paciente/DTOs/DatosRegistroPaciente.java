package med.vol.api.Domain.Paciente.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.Domain.Direccion.DatosDireccion;
import med.vol.api.Domain.Direccion.Direccion;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotBlank
        String telefono,
        @NotNull
        @Valid
        DatosDireccion direccion
) {
}
