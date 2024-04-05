package br.acsousa.javagpt1.repositories;

import br.acsousa.javagpt1.models.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Long>{

}
