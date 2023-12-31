package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.domain.UserAccount;
import com.my.javaspringpractice.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserAccountRepository extends JpaRepository<UserAccount, UserId> {
}
