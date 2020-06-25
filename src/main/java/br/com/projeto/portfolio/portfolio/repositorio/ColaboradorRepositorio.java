package br.com.projeto.portfolio.portfolio.repositorio;

import br.com.projeto.portfolio.portfolio.entidades.Colaborador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColaboradorRepositorio extends CrudRepository<Colaborador, Long> {

    public List<Colaborador> findByCpf(String cpf);


}
