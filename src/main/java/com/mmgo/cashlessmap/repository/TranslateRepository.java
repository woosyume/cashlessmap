package com.mmgo.cashlessmap.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmgo.cashlessmap.entity.Translate;

@Repository
public interface TranslateRepository extends JpaRepository<Translate, Long> {
	
}