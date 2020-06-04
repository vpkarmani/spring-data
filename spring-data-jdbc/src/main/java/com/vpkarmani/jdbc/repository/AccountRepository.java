package com.vpkarmani.jdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vpkarmani.jdbc.model.Account;

@Repository
public class AccountRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Account findByAccountNumber(String accountNumber) {
    final String sql = "SELECT * FROM account WHERE accoun_number = ?";
    return jdbcTemplate.queryForObject(sql, new String[] { accountNumber }, new AccountRowMapper());
  }

  public List<Account> findByAccountType(Account.Type accountType) {
    final String sql = "SELECT * FROM account WHERE accoun_number = ?";
    return jdbcTemplate.query(sql, new String[] { accountType.name() }, new AccountRowMapper());
  };

  class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
      final Account account = new Account();
      account.setId(rs.getString("id"));
      account.setAccountNumber(rs.getString("account_number"));
      account.setAccountType(Account.Type.valueOf(rs.getString("account_type")));
      account.setBalance(rs.getDouble("balance"));
      return account;
    }

  }

  public void deleteAll() {
    final String sql = "DELETE  FROM account";
    jdbcTemplate.execute(sql);

  }

  public void save(Account account) {
    jdbcTemplate.update("INSERT INTO account VALUES (?, ?, ?, ?)", account.getId(), account.getAccountNumber(),
        account.getAccountType().name(), account.getBalance());
  }

  public List<Account> findAll() {
    final String sql = "SELECT * FROM account";
    return jdbcTemplate.query(sql, new AccountRowMapper());
  }

}
