package med.vol.api.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.Domain.Medico.DTOs.DatosActualizarMedico;
import med.vol.api.Domain.Medico.DTOs.DatosListadoMedico;
import med.vol.api.Domain.Medico.DTOs.DatosRegistroMedico;
import med.vol.api.Domain.Medico.DTOs.DatosRespuestaMedico;
import med.vol.api.Domain.Medico.Medico;
import med.vol.api.Domain.Medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Medico")
public class MedicoController {
    @Autowired
    MedicoRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                          UriComponentsBuilder uriComponentsBuilder){
        Medico medico = repository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico);

        //retornar el codigo 204
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return  ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> lsitadoMedicos(@PageableDefault(sort = "nombre") Pageable paginacion){
//        return repository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(repository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retormarDatosNyId(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody DatosActualizarMedico datosActualizarMedico){
        Medico medico = repository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarMedico(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }

    //Delete Loigco
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    //Deletea directamente en la base de datos
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = repository.getReferenceById(id);
//        repository.delete(medico);
//    }
}
