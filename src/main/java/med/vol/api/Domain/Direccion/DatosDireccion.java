package med.vol.api.Domain.Direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotNull
        Integer numero,
        @NotBlank
        String complemento
) {
    public DatosDireccion(Direccion direccion) {
        this(direccion.getCalle(), direccion.getDistrito(), direccion.getCiudad(),
                direccion.getNumero(), direccion.getComplemento());
    }
}
