package com.vpkarmani.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vpkarmani.redis.model.Account;
import com.vpkarmani.redis.repository.AccountRepository;

@SpringBootApplication
public class SpringDataRedisApplication implements CommandLineRunner {

  @Autowired
  private AccountRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(SpringDataRedisApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();

    // save few accounts
    repository.save(new Account("123456", Account.Type.SAVINGS, Double.valueOf(100)));
    repository.save(new Account("567890", Account.Type.CHECKING, Double.valueOf(200)));
    repository.save(new Account("456743", Account.Type.SAVINGS, Double.valueOf(50)));

    // fetch all accounts
    System.out.println("Accounts found with findAll():");
    System.out.println("-------------------------------");
    for (final Account account : repository.findAll()) {
      System.out.println(account);
    }

    // fetch an individual account
    System.out.println("Account found with findByAccountNumber('123456'):");
    System.out.println("--------------------------------");
    System.out.println(repository.findByAccountNumber("123456"));

    System.out.println("Accounts found with findByAccountType('SAVINGS'):");
    System.out.println("--------------------------------");
    for (final Account account : repository.findByAccountType(Account.Type.SAVINGS)) {
      System.out.println(account);
    }

  }
}
