package devinhouse.elm.projetofinal.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class InteressadoCadastroDto {
    private String nome;
    private String identificacao;
    private LocalDate dataDeNascimento;
    private boolean ativo = true;
}
