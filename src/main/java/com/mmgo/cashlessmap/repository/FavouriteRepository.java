package com.mmgo.cashlessmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.mmgo.cashlessmap.entity.Favourite;

@Repository
public interface FavouriteRepository extends  JpaRepository<Favourite, Long>  {
	
	@Query(value="SELECT username,storeid FROM favourite WHERE username=:username", nativeQuery = true)
	List<String> findByUsername(@Param("username") String username);
	
	@Query(value="DELETE FROM favourite WHERE storeid=:storeid", nativeQuery = true)
	void removeByStoreId(@Param("storeid") String storeid);
	
	@Query(value="SELECT storeid FROM favourite WHERE storeid=:storeid", nativeQuery = true)
	String findByStoreId(@Param("storeid") String storeid);
}
