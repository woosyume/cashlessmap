package com.mmgo.cashlessmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mmgo.cashlessmap.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT username FROM user WHERE username = :username", nativeQuery = true)
	String findByUsername(@Param("username") String username);
}
