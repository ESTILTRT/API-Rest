package med.vol.api.Domain.Medico.DTOs;

import med.vol.api.Domain.Direccion.DatosDireccion;
import med.vol.api.Domain.Medico.Medico;

public record DatosRespuestaMedico(
        Long id,
        String nomber,
        String email,
        String telefono,
        String documento,
        DatosDireccion direccion) {
    public DatosRespuestaMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getDocumento(), new DatosDireccion(medico.getDireccion()));
    }
}
