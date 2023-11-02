package com.my.javaspringpractice.repository;

import com.my.javaspringpractice.domain.UserAccount;
import com.my.javaspringpractice.domain.UserId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface UserAccountRepository extends CrudRepository<UserAccount, UserId> {
}