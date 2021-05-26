package devinhouse.elm.projetofinal.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import devinhouse.elm.projetofinal.model.Processo;


@Repository
public interface ProcessosRepository extends JpaRepository<Processo, Integer> {
    int countBySiglaEquals(String sigla);
    boolean existsByChave(String chave);
}
