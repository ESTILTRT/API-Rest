package med.vol.api.Controller;

import lombok.With;
import med.vol.api.Domain.Consulta.AgendaDeConsultaService;
import med.vol.api.Domain.Consulta.DTOs.DatosAgendarConsulta;
import med.vol.api.Domain.Consulta.DTOs.DatosDetalleConsulta;
import med.vol.api.Domain.Medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosAgendarConsulta> agendarConsultaJacksonTester;

    @Autowired
    private JacksonTester<DatosDetalleConsulta> detalleConsultaJacksonTesterl;

    @MockBean
    private AgendaDeConsultaService agendaDeConsultaService;

    @Test
    @DisplayName("Deberia retornar estado HTTP 400 cuando los datos ingresados " +
            "sean invalidos ")
    @WithMockUser
    void agenderEscenario1() throws Exception {
        //given //when
        var respuesta = mvc.perform(post("/cosulta")).andReturn().getResponse();

        //then
        assertThat(respuesta.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deberia retornar estado HTTP 200 cuando los datos ingresados " +
            "sean validos ")
    @WithMockUser
    void agenderEscenario2() throws Exception {
        //given
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datos = new DatosDetalleConsulta(null, 2l, 5l, fecha);
        // when

        when(agendaDeConsultaService.agender(any())).thenReturn(datos);

        var respuesta = mvc.perform(post("/cosulta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(agendarConsultaJacksonTester.write(new DatosAgendarConsulta(2l,5l, especialidad,
                        fecha)).getJson())
        ).andReturn().getResponse();

        //then
        assertThat(respuesta.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = detalleConsultaJacksonTesterl.write(datos).getJson();

        assertThat(respuesta.getContentAsString()).isEqualTo(jsonEsperado);
    }
}