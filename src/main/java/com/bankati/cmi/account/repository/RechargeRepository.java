package com.bankati.cmi.account.repository;

import com.bankati.cmi.account.model.RechargeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeRepository extends JpaRepository<RechargeTransaction, Long> {

}
