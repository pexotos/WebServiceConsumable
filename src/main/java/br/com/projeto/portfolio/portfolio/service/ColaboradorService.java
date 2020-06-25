package br.com.projeto.portfolio.portfolio.service;


import br.com.projeto.portfolio.portfolio.entidades.Colaborador;
import br.com.projeto.portfolio.portfolio.repositorio.ColaboradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {

    @Autowired
    ColaboradorRepositorio colaboradorRepo;

    public void salvarColaborador(Colaborador colaborador) throws Exception{
        try {
            List<Colaborador> buscaColaborador = colaboradorRepo.findByCpf(colaborador.getCpf());
            if(buscaColaborador!=null && !buscaColaborador.isEmpty()){
                if(!buscaColaborador.get(0).equals(colaborador)){
                    colaborador.setId(buscaColaborador.get(0).getId());
                    colaboradorRepo.save(colaborador);
                    return;
                }
//                System.out.println("======================== COLABORADOR JÁ ESTAVA NA BASE DE DADOS ========================");
                throw new Exception();
            } else {
                System.out.println(" ======================== Entrou no else e salvou!! ========================");
                colaboradorRepo.save(colaborador);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }


    }

    public void deletarColaborador(Colaborador colaborador)  throws Exception{
        try {
            colaboradorRepo.delete(colaborador);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public List<Colaborador> retornaListaColaboradors() throws Exception{
        try {
            System.out.println("=====exec 2");
            List<Colaborador> allCol = (List<Colaborador>) colaboradorRepo.findAll();
            System.out.println(allCol.get(0).getNome());
            return allCol;
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


//    private List<Colaborador> castToEmpresaClienteObject(List<Object[]> colabMap) {
//
//        List<Colaborador> retorno = convertList(colabMap, colaborador -> new Colaborador(colaborador));
//        return retorno;
//    }
//
//    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
//        return from.stream().map(func).collect(Collectors.toList());
//    }
}
