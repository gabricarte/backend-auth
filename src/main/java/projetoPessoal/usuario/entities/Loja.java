    package projetoPessoal.usuario.entities;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "tb_lojas")

    public class Loja {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String nomeLoja;
        private LocalDate dataAtualizacaoDados;
        private String cnpj;
        private String senha;
        @ManyToOne
        @JoinColumn(name = "role_id")
        private Role role;
    }
