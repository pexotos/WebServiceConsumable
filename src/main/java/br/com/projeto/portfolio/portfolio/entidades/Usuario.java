package br.com.projeto.portfolio.portfolio.entidades;

import br.com.projeto.portfolio.portfolio.enums.TipoUsuario;

import javax.persistence.*;

@Entity
@Table(name = "usuario_sistema")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Colaborador colaborador;

    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipoUsuario;

    @Column(name = "LOGIN", nullable = false, length = 50)
    private String login;

    @Column(name = "SENHA", nullable = false, length = 100)
    private String senha;



}
