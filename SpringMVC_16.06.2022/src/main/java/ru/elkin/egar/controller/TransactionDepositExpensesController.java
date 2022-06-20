package ru.elkin.egar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.egar.entity.*;
import ru.elkin.egar.repo.DepositRepo;
import ru.elkin.egar.repo.ExpensesRepo;
import ru.elkin.egar.repo.TransactionDepositExpensesRepo;
import ru.elkin.egar.service.TransactionDepositExpensesService;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/transaction_deposit_expenses")
public class TransactionDepositExpensesController implements TransferEntityControllerInterface {

    private final TransactionDepositExpensesRepo transactionDepositExpensesRepo;
    private final DepositRepo depositRepo;
    private final ExpensesRepo expensesRepo;
    private final TransactionDepositExpensesService transactionDepositExpensesService;

    @Autowired
    public TransactionDepositExpensesController(TransactionDepositExpensesRepo transactionDepositExpensesRepo,
                                                DepositRepo depositRepo,
                                                ExpensesRepo expensesRepo,
                                                TransactionDepositExpensesService transactionDepositExpensesService) {
        this.transactionDepositExpensesRepo = transactionDepositExpensesRepo;
        this.depositRepo = depositRepo;
        this.expensesRepo = expensesRepo;
        this.transactionDepositExpensesService = transactionDepositExpensesService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        model.addAttribute("transactionDepositExpensesList", transactionDepositExpensesRepo.findAllTransaction());
        return "transaction_deposit_expenses";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        TransactionDepositExpenses transactionDepositExpenses = new TransactionDepositExpenses();
        model.addAttribute("transactionDepositExpenses",transactionDepositExpenses);
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("selectedDeposit", new Deposit());
        model.addAttribute("expensesList", expensesRepo.findAllByOrderById());
        model.addAttribute("selectedExpenses", new Expenses());
        // запуск сервиса обновления deposit и expenses

        return "transaction_deposit_expenses_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        TransactionDepositExpenses transactionDepositExpenses = transactionDepositExpensesRepo.findById(id).orElseThrow();
        model.addAttribute("transactionDepositExpenses", transactionDepositExpenses);
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("selectedDeposit", transactionDepositExpenses.getDeposit());
        model.addAttribute("expensesList", expensesRepo.findAllByOrderById());
        model.addAttribute("selectedExpenses", transactionDepositExpenses.getExpenses());
        return "transaction_deposit_expenses_edit";
    }


    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @RequestParam Map<String, String> model) throws CloneNotSupportedException {
        TransactionDepositExpenses
                item = new TransactionDepositExpenses(),
                oldItem = new TransactionDepositExpenses();
        if (id != null) {
            item = transactionDepositExpensesRepo.findById(id).orElseThrow();
            oldItem = (TransactionDepositExpenses) item.clone();
            oldItem.setDeposit((Deposit) item.getDeposit().clone());
            oldItem.setExpenses((Expenses) item.getExpenses().clone());
        }

        item.setDeposit(depositRepo.findById(Long.valueOf(model.get("selectedDeposit"))).orElseThrow());
        item.setExpenses(expensesRepo.findById(Long.valueOf(model.get("selectedExpenses"))).orElseThrow());

        for (String key : model.keySet()) {
            switch (key){
                case "Money":
                    item.setMoney(Long.valueOf(model.get(key)));
                    break;
                case "Date":
                    item.setDate(model.get(key).equals("")?null: LocalDate.parse(model.get(key)));
                    break;
            }
        }
        if (id == null) {
            transactionDepositExpensesService.transactionSave(item);
        } else {
            transactionDepositExpensesService.transactionEdit(item, oldItem);
        }
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        TransactionDepositExpenses transactionDepositExpenses = transactionDepositExpensesRepo.findById(id).orElseThrow();
        transactionDepositExpensesService.transactionDelete(transactionDepositExpenses);
        return "redirect:/";
    }

}
