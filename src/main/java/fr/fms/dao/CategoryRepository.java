package fr.fms.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.fms.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	public Optional<Category> findById(Long Id);
	
	public void deleteById(Long Id);
	
	Page<Category> findByDescriptionContains(String description, Pageable pageable);
}
