package devinhouse.elm.projetofinal.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IdentificacaoValidatorTest {

    @InjectMocks
    private IdentificacaoValidator validator;

    @Test
    public void deveDarSucessoAoValidarIdentificacao() {
        var cpf = "098.592.539-60";

        var isValid = validator.isValid(cpf);

        assertTrue(isValid);
    }

    @Test
    public void deveDarFalhaAoValidarIdentificacaoInvalida() {
        var cpf = "098.592.539-00";

        var isValid = validator.isValid(cpf);

        assertFalse(isValid);
    }

    @Test
    public void deveDarFalhaAoValidarIdentificacaoRepetida() {
        var cpf = "000.000.000-00";

        var isValid = validator.isValid(cpf);

        assertFalse(isValid);
    }
}
