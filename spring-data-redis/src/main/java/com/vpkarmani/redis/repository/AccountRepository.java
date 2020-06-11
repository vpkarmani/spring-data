package com.vpkarmani.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vpkarmani.redis.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

}
