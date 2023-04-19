package com.sungchul.jpatest.domain.repository;

import com.sungchul.jpatest.domain.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
    //모든 목록 출력
    List<AdminUser> findAll();

    List<AdminUser> findTop10ByOrderByUserId();
    // findTop10ByOrderBy


    //name이 일치하는 목록 출력
    List<AdminUser> findAllByName(String name);

    //
    List<AdminUser> findAllByUserIdContaining(int status);
}
