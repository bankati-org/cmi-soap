package com.bankati.cmi.account.repository;

import com.bankati.cmi.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findAccountByAccountNumber(String accountNumber);
    Account findAccountByOwnerCin(String ownerCin);
    boolean existsAccountByOwnerCin(String cin);
    boolean existsByAccountNumber(String accountNumber);
}
