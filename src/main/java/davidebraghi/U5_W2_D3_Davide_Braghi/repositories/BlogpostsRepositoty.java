package davidebraghi.U5_W2_D4_Davide_Braghi.repositories;

import davidebraghi.U5_W2_D3_Davide_Braghi.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogpostsRepositoty extends JpaRepository<Blogpost, Integer> {
    Optional<Blogpost> findByTitle(String title);
}
