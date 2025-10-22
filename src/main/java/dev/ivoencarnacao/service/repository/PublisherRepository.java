package dev.ivoencarnacao.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ivoencarnacao.service.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

  Optional<Publisher> findByName(String name);

}
