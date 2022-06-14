package ru.elkin.egar.repo.customRepo;

import ru.elkin.egar.entity.TransactionDepositExpenses;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomRepoImpl implements CustomRepo<TransactionDepositExpenses> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TransactionDepositExpenses> findAllTransactional() {
        return em.createQuery("select t from TransactionDepositExpenses t join fetch t.deposit join fetch t.expenses", TransactionDepositExpenses.class)
                .getResultList();
    }
}
