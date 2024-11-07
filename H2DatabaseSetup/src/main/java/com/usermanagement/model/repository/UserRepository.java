package com.usermanagement.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.usermanagement.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	@Query(value = "select * from tbl_users s where s.firstname like %:keyword% or s.address like %:keyword%", nativeQuery = true)
	 List<UserEntity> findByKeyword(@Param("keyword") String keyword);
	
	@Query(value = "select  * from TBL_USERS where year(CURRENT_DATE())-year(dateofbirth)   BETWEEN :minage AND :maxage", nativeQuery = true)
	List<UserEntity> findByAge(@Param("minage") Number minage,@Param("maxage") Number maxage);
	
}
