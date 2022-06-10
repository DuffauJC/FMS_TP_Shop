package fr.fms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.fms.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	public Optional<Category> findById(Long Id);
	public default void update(Category category, Long Id)   
	{  
	save(category);  
	} 
	public void deleteById(Long Id);
}
