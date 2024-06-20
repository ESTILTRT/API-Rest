package med.vol.api.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.Domain.Paciente.DTOs.DatosListadoPacientes;
import med.vol.api.Domain.Paciente.DTOs.DatosRegistroPaciente;
import med.vol.api.Domain.Paciente.DTOs.DatosRespuestaPaciente;
import med.vol.api.Domain.Paciente.Paciente;
import med.vol.api.Domain.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Pacientes")
public class PacientesController {

    @Autowired
    public PacienteRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                    UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = repository.save(new Paciente(datosRegistroPaciente));
        DatosRespuestaPaciente respuesta = new DatosRespuestaPaciente(paciente);
        URI url = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return  ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> optenerPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        DatosRespuestaPaciente respuestaPaciente = new DatosRespuestaPaciente(paciente);
        return ResponseEntity.ok(respuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPacientes>> listaPacientes(@PageableDefault(sort = "nombre") Pageable paginacion){
        return ResponseEntity.ok(repository.findByActivoTrue(paginacion).map(DatosListadoPacientes::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getById(id);
        paciente.eliminarPaciente();
        return ResponseEntity.noContent().build();
    }
}
