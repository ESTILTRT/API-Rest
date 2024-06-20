package med.vol.api.Domain.Medico.DTOs;

import med.vol.api.Domain.Medico.Medico;

public record DatosListadoMedico(Long id, String nombre, String especialidad, String documento, String email) {

    public DatosListadoMedico(Medico medico){
        this(medico.getId(),medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
