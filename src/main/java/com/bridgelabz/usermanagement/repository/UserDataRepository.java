package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Query(value = "select count(*) from user_data where worong_login_attempt < 3", nativeQuery = true)
    Integer getActiveUserCount();

    @Query(value = "select count(*) from user_data where worong_login_attempt > 2", nativeQuery = true)
    Integer getInactiveUserCount();

    @Modifying
    @Transactional
    @Query(value = "update user_data set worong_login_attempt = :attempts where id = :id", nativeQuery = true)
    void updateWrongAttempts(@Param("id") Long id, @Param("attempts") int attempts);

    @Modifying
    @Transactional
    @Query(value = "update user_data set worong_login_attempt = 0, last_login = :loginTime where id = :id", nativeQuery = true)
    void updateLoginDetails(@Param("id") Long id, @Param("loginTime") LocalDateTime loginTime);

    @Modifying
    @Transactional
    @Query(value = "update user_data set status = :status where id = :id", nativeQuery = true)
    void updateStatus(@Param("id") Long id, @Param("status") String status);

    @Query(value = "SELECT * FROM user_data ORDER BY registration_date DESC LIMIT 10", nativeQuery = true)
    List<UserData> getRecentTegistration();

    @Query(value = "select count(*) from user_data where status ='Active'", nativeQuery = true)
    Integer getOnlineUserCount();

    @Query(value = "select * from user_data order by registration_date ASC", nativeQuery = true)
    List<UserData> getUserAscByRegistrationDate();
}
