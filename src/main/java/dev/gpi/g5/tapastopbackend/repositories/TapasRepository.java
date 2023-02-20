package dev.gpi.g5.tapastopbackend.repositories;

import dev.gpi.g5.tapastopbackend.models.Tapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TapasRepository extends JpaRepository<Tapa, Long> {

}
