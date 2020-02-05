package co.studycode.xyz.controllers;


import co.studycode.xyz.models.Transactions;
import co.studycode.xyz.repositories.TransactionRepository;
import co.studycode.xyz.repositories.UserRepository;
import co.studycode.xyz.requests.TransactionRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Transactions" ,description = "Users Transactions")
@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/createtransaction")
    public Transactions creatTransaction( @Valid @RequestBody TransactionRequest transactionRequest) {
        Transactions transactions = new Transactions();
        transactions.setAmount(transactionRequest.getAmount());
        transactions.setPhone(transactionRequest.getPhone());


        return transactionRepository.save(transactions);


    }

    @GetMapping("/all")
    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
