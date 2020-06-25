package br.com.projeto.portfolio.portfolio.controle;

import br.com.projeto.portfolio.portfolio.entidades.Colaborador;
import br.com.projeto.portfolio.portfolio.enums.DefaultResponseEnum;
import br.com.projeto.portfolio.portfolio.generic.response.GenericResponse;
import br.com.projeto.portfolio.portfolio.service.ColaboradorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.xml.ws.WebServiceException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorControle {
    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping(value = "/salvar", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse<String> salvar(@RequestBody(required = true) Colaborador colaborador) throws WebServiceException {
        System.out.println(" ======================== CHAMOU ======================== ");
        try {
            if (colaborador == null) {
                return new GenericResponse<>(DefaultResponseEnum.ERROR.returnCode, DefaultResponseEnum.ERROR.returnMessage, "Erro ao salvar");
            }
            try {
                colaboradorService.salvarColaborador(colaborador);
            } catch (Exception e) {
                return new GenericResponse<>(DefaultResponseEnum.ERROR.returnCode, DefaultResponseEnum.ERROR.returnMessage, "Erro ao salvar");
            }

            return new GenericResponse<>(DefaultResponseEnum.OK.returnCode, DefaultResponseEnum.OK.returnMessage, "Salvo com sucesso!");
        } catch (Exception e) {
            return new GenericResponse<>(DefaultResponseEnum.ERROR.returnCode, DefaultResponseEnum.ERROR.returnMessage, "Erro ao salvar");
        }

    }

    @GetMapping(value = "/listar")
    public @ResponseBody
    GenericResponse<String> retornaListaColaborador() {
        System.out.println("======== executou");
        try {
            List<Colaborador> colList = colaboradorService.retornaListaColaboradors();
            String jsonObj = toJason(colList);
            System.out.println("==========  ?? === " + jsonObj);
            return new GenericResponse<String>(DefaultResponseEnum.OK.returnCode, DefaultResponseEnum.OK.returnMessage, jsonObj);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping(value = "/deletar", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse<String> deletar(@RequestBody(required = true) Colaborador colaborador) throws WebServiceException {
        System.out.println(" ======================== DELETAR ======================== ");
        try {
            if (colaborador == null) {
                return new GenericResponse<>(DefaultResponseEnum.ERROR.returnCode, DefaultResponseEnum.ERROR.returnMessage, "Erro ao salvar");
            }
            try {
                colaboradorService.deletarColaborador(colaborador);
            } catch (Exception e) {
                return new GenericResponse<>(DefaultResponseEnum.ERROR.returnCode, DefaultResponseEnum.ERROR.returnMessage, "Erro ao salvar");
            }

            return new GenericResponse<>(DefaultResponseEnum.OK.returnCode, DefaultResponseEnum.OK.returnMessage, "Salvo com sucesso!");
        } catch (Exception e) {
            return new GenericResponse<>(DefaultResponseEnum.ERROR.returnCode, DefaultResponseEnum.ERROR.returnMessage, "Erro ao salvar");
        }

    }


    public String toJason(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(obj);
            System.out.println("ResultingJSONstring = " + json);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
