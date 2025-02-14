package com.bankati.cmi.transaction.soap;

import com.bankati.cmi.transaction.ProcessTransactionRequest;
import com.bankati.cmi.transaction.ProcessTransactionResponse;
import com.bankati.cmi.transaction.dto.TransactionDto;
import com.bankati.cmi.transaction.service.TransactionService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class TransactionEndpoint {
    private static final String NAMESPACE_URI = "http://bankati.com/cmi/transaction";
    private final TransactionService transactionService;

    public TransactionEndpoint(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProcessTransactionRequest") // Changé de "ProcessTransaction"
    @ResponsePayload
    public ProcessTransactionResponse processTransaction(@RequestPayload ProcessTransactionRequest processTransactionRequest) {
        return transactionService.processTransaction(processTransactionRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTransactions")
    @ResponsePayload
    public List<TransactionDto> getTransactions(@RequestPayload String accountNumber) {
        return transactionService.getTransactions(accountNumber);
    }
}