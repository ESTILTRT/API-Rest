package med.vol.api.Domain.Paciente.DTOs;

import med.vol.api.Domain.Direccion.DatosDireccion;
import med.vol.api.Domain.Paciente.Paciente;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String documento,
        String telefono,
        DatosDireccion direccion) {
    public DatosRespuestaPaciente(Paciente paciente) {
        this(paciente.getId(),paciente.getNombre(),paciente.getDocumento(),paciente.getTelefono(),
                new DatosDireccion(paciente.getDireccion()));
    }
}
