package med.vol.api.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.Domain.Consulta.AgendaDeConsultaService;
import med.vol.api.Domain.Consulta.CancelarConsultaService;
import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import med.vol.api.Domain.Consulta.DTOs.DatosCancelarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cosulta")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Consultas")
public class ConsultaController {


    @Autowired
    private AgendaDeConsultaService agenderService;
    @Autowired
    private CancelarConsultaService cancelarService;

    @PostMapping
    @Transactional
    public ResponseEntity agender(@RequestBody @Valid DatosAgendarConsulta datos){
        var response = agenderService.agender(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelarConsulta datos){
        cancelarService.CancelarConsulta(datos);
        return ResponseEntity.noContent().build();
    }
}
