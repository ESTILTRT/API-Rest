package med.vol.api.Domain.Medico;

import med.vol.api.Domain.Consulta.Consulta;
import med.vol.api.Domain.Direccion.DatosDireccion;
import med.vol.api.Domain.Medico.DTOs.DatosRegistroMedico;
import med.vol.api.Domain.Paciente.DTOs.DatosRegistroPaciente;
import med.vol.api.Domain.Paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deberia retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario1() {
        var proximoLunes = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico=registrarMedico("Jose","j@mail.com","123456",Especialidad.CARDIOLOGIA);
        var paciente= registrarPaciente("antonio","654321");
        registrarConsulta(medico,paciente,proximoLunes);

        var medicoLibre = repository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA, proximoLunes);
        //then
        assertThat(medicoLibre).isNull();
    }
    @Test
    @DisplayName("Deberia retornar un medico cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario2() {
        //give
        var proximoLunes = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico=registrarMedico("Jose","j@mail.com","123456",Especialidad.CARDIOLOGIA);

        //when
        var medicoLibre = repository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA, proximoLunes);

        //then
        assertThat(medicoLibre).isEqualTo(medico);
    }
    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, documento));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "61999999999",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre,  String documento) {
        return new DatosRegistroPaciente(
                nombre,
                documento,
                "61999999999",
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "Wolf Street",
                "Sheep",
                "Los Anjeles",
                44,
                "Pipo"
        );
    }
}