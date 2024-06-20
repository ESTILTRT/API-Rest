package med.vol.api.Domain.Consulta;


import med.vol.api.Domain.Consulta.DTOs.DatosCancelarConsulta;
import med.vol.api.Domain.Consulta.Validaciones.Eliminar.ValidarEliminarConsulta;
import med.vol.api.Infra.Erros.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelarConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    List<ValidarEliminarConsulta> validaciones;

    public void CancelarConsulta(DatosCancelarConsulta datos){
        if(!repository.findById(datos.idConsulta()).isPresent()){
            throw new ValidacionDeIntegridad("La consulta digitada no existe...");
        }
        validaciones.forEach(v -> v.validar(datos));
        var consulta = repository.getReferenceById(datos.idConsulta());
        repository.delete(consulta);
    }
}
