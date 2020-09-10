package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByEmail(String email);

    Optional<Object> findByPhone(Long phone);

    Optional<Object> findByPhoneext(Long phoneext);

    @Query(value = "select count(*) from user_data", nativeQuery = true)
    Integer getCountOfUser();

    @Query(value = "select count(*) from user_data where firstname LIKE %:keyword% OR lastname LIKE %:keyword% OR email LIKE %:keyword%", nativeQuery = true)
    Integer getCountOfSearchUser(@Param("keyword") String keyword);

    @Query(value = "select * from user_data LIMIT :lowerlimit , :upperlimit", nativeQuery = true)
    List<UserData> getUserDataForList(@Param("lowerlimit") int lowerlimit, @Param("upperlimit") int upperlimit);

    @Query(value = "select * from user_data where firstname LIKE %:keyword% OR lastname LIKE %:keyword% OR email LIKE %:keyword% LIMIT :lowerlimit , :upperlimit", nativeQuery = true)
    List<UserData> getUserDataForListWithSearch(@Param("lowerlimit") int lowerlimit, @Param("upperlimit") int upperlimit, @Param("keyword") String keyword);
}
