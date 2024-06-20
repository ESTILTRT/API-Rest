package med.vol.api.Domain.Medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.Domain.Medico.DTOs.DatosActualizarMedico;
import med.vol.api.Domain.Medico.DTOs.DatosRegistroMedico;
import med.vol.api.Domain.Direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(@Valid DatosRegistroMedico medico) {
        this.nombre = medico.nombre();
        this.email = medico.email();
        this.telefono = medico.telefono();
        this.documento = medico.documento();
        this.activo = true;
        this.especialidad = medico.especialidad();
        this.direccion = new Direccion(medico.direccion());
    }

    public void actualizarMedico(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.documento() != null){
            this.documento = datosActualizarMedico.documento();
        }
        if (datosActualizarMedico.direccion() != null){
            this.direccion = direccion.actualizarDireccion(datosActualizarMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
