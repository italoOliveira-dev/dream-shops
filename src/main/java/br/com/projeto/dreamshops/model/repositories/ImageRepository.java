package br.com.projeto.dreamshops.model.repositories;

import br.com.projeto.dreamshops.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
