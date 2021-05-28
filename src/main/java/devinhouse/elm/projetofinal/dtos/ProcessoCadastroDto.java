package devinhouse.elm.projetofinal.dtos;

import lombok.*;

import devinhouse.elm.projetofinal.model.Assunto;


@Getter
@Setter
public class ProcessoCadastroDto {
    private String sigla;
    private String descricao;
    private Assunto assunto;
}
