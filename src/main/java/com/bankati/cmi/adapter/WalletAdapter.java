package com.bankati.cmi.adapter;

import com.bankati.cmi.dto.CreateWalletRequest;
import com.bankati.cmi.dto.depositRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Wallet-ms")
@RibbonClient(name = "Wallet-ms")
public interface WalletAdapter {
    @PostMapping("/wallet/wallets/create")
    ResponseEntity<?> createWallet(@RequestBody CreateWalletRequest request);

    @PostMapping("/wallet/wallets/create")
    ResponseEntity<?> depositToWallet(@RequestBody depositRequest request);
}
