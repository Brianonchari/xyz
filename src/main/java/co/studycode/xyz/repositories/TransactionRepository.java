package co.studycode.xyz.repositories;

import co.studycode.xyz.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository  extends JpaRepository< Transactions,Long> {

    List<Transactions> findAll();
}
