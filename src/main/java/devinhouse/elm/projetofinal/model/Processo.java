package devinhouse.elm.projetofinal.model;

import lombok.*;
import static lombok.AccessLevel.NONE;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonProperty;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import java.time.Year;


@Entity
@Data
public class Processo {

    @Column(name = "ID", nullable = false) @Id @GeneratedValue(strategy = IDENTITY) private int id;
    @Column(name = "NUPROCESSO", nullable = false) private int numero;
    @Column(name = "SGORGAOSETOR", length = 4, nullable = false) private String sigla;
    @Column(name = "NUANO", length = 4, nullable = false) private Year ano;
    @Column(name = "CHAVEPROCESSO", length = 250, unique = true, nullable = false) private String chave;
    @Column(name = "DESCRICAO", length = 250, nullable = false) private String descricao;
    // TO DO: campos com assunto e interessados
}
