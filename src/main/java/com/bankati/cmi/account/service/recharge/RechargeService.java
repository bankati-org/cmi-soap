package com.bankati.cmi.account.service.recharge;

import com.bankati.cmi.account.AccountRechargeRequest;
import com.bankati.cmi.account.AccountRechargeResponse;

public interface RechargeService {
    AccountRechargeResponse processRecharge(AccountRechargeRequest accountRechargeRequest);
}
