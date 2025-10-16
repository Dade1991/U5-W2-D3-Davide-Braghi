package davidebraghi.U5_W2_D3_Davide_Braghi.repositories;

import davidebraghi.U5_W2_D3_Davide_Braghi.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);
}
