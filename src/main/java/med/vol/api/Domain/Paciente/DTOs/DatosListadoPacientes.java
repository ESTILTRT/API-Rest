package med.vol.api.Domain.Paciente.DTOs;

import med.vol.api.Domain.Paciente.Paciente;

public record DatosListadoPacientes(Long id, String nombre,String documento, String telefono) {
    public DatosListadoPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getDocumento(), paciente.getTelefono());
    }
}
