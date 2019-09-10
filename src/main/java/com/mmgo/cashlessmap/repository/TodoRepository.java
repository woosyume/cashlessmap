package com.mmgo.cashlessmap.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmgo.cashlessmap.entity.Todo;

/**
 * このクラスは、データベースとアクセスするためのリポジトリークラスです。
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	
}