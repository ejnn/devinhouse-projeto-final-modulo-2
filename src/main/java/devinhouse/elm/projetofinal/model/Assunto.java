package devinhouse.elm.projetofinal.model;

import lombok.*;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonProperty;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import java.time.LocalDateTime;


@Data
@Entity
public class Assunto {

    @Id @GeneratedValue(strategy = IDENTITY) @JsonProperty(access = READ_ONLY) private long id;
    @Column(nullable=false) private String descricao;
    @Column(nullable=false) @JsonProperty(access = READ_ONLY) private final LocalDateTime dataCadastro = LocalDateTime.now();
    @Column(nullable=false) private boolean ativo = true;
}
