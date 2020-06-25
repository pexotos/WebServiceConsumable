package br.com.projeto.portfolio.portfolio.entidades;

import javax.persistence.*;

@Entity
@Table(name = "colaborador")
public class Colaborador extends Pessoa {
    public Colaborador() {
    }

    public Colaborador(Object[] obj){
        this.setCpf((String)obj[0]);
        this.setId((long)obj[0]);
        this.setAtivo((boolean)obj[0]);
        this.setRg((String)obj[0]);
        this.setNome((String)obj[0]);
        this.setCtps((String)obj[0]);
    }

    public Colaborador(String nome, String cpf) {
        super.setNome(nome);
        super.setCpf(cpf);
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Usuario usuario;

    @Column(name = "CTPS")
    private String ctps;

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }
}
