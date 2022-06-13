package fr.fms.dao;

import java.util.List;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.fms.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

	public Optional<Article> findById(Long Id);
	
	public List<Article> findByCategoryId(Long categoryId);
	
	public void deleteById(Long Id);
}
