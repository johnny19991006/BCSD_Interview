package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Category;
import BCSD.MusicStream.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
