package med.vol.api.Domain.Paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.Domain.Direccion.Direccion;
import med.vol.api.Domain.Paciente.DTOs.DatosRegistroPaciente;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String documento;
    private String telefono;
    private Boolean activo;
    @Embedded
    private Direccion direccion;

    public Paciente(@Valid DatosRegistroPaciente datosRegistroPaciente) {
        this.nombre = datosRegistroPaciente.nombre();
        this.documento = datosRegistroPaciente.documento();
        this.telefono = datosRegistroPaciente.telefono();
        this.activo = true;
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public void eliminarPaciente() {
        this.activo = false;
    }
}
