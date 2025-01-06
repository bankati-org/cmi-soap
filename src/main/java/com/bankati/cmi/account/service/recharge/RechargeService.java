package com.bankati.cmi.account.service.recharge;

import com.bankati.cmi.account.AccountRechargeRequest;
import com.bankati.cmi.account.AccountRechargeResponse;
import com.bankati.cmi.account.WalletDepositRequest;
import com.bankati.cmi.account.WalletDepositResponse;

public interface RechargeService {
    AccountRechargeResponse processRecharge(AccountRechargeRequest accountRechargeRequest);
    WalletDepositResponse depositToWallet(WalletDepositRequest walletDepositRequest);
}
