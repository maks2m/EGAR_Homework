package ru.elkin.egar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.egar.entity.*;
import ru.elkin.egar.repo.DepositRepo;
import ru.elkin.egar.repo.IncomeRepo;
import ru.elkin.egar.repo.TransactionIncomeDepositRepo;
import ru.elkin.egar.service.TransactionIncomeDepositService;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/transaction_income_deposit")
public class TransactionIncomeDepositController implements TransferEntityControllerInterface {

    private final TransactionIncomeDepositRepo transactionIncomeDepositRepo;
    private final IncomeRepo incomeRepo;
    private final DepositRepo depositRepo;
    private final TransactionIncomeDepositService transactionIncomeDepositService;

    @Autowired
    public TransactionIncomeDepositController(TransactionIncomeDepositRepo transactionIncomeDepositRepo,
                                              IncomeRepo incomeRepo,
                                              DepositRepo depositRepo,
                                              TransactionIncomeDepositService transactionIncomeDepositService) {
        this.transactionIncomeDepositRepo = transactionIncomeDepositRepo;
        this.incomeRepo = incomeRepo;
        this.depositRepo = depositRepo;
        this.transactionIncomeDepositService = transactionIncomeDepositService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        model.addAttribute("transactionIncomeDepositList", transactionIncomeDepositRepo.findAllTransaction());
        return "transaction_income_deposit";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        TransactionIncomeDeposit transactionIncomeDeposit = new TransactionIncomeDeposit();
        model.addAttribute("transactionIncomeDeposit",transactionIncomeDeposit);
        model.addAttribute("incomeList", incomeRepo.findAllByOrderById());
        model.addAttribute("selectedIncome", new Income());
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("selectedDeposit", new Deposit());
        // запуск сервиса обновления income и deposit
        return "transaction_income_deposit_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        TransactionIncomeDeposit transactionIncomeDeposit = transactionIncomeDepositRepo.findById(id).orElseThrow();
        model.addAttribute("transactionIncomeDeposit", transactionIncomeDeposit);
        model.addAttribute("incomeList", incomeRepo.findAllByOrderById());
        model.addAttribute("selectedIncome", transactionIncomeDeposit.getIncome());
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("selectedDeposit", transactionIncomeDeposit.getDeposit());

        return "transaction_income_deposit_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @RequestParam Map<String, String> model) throws CloneNotSupportedException {
        TransactionIncomeDeposit
                item = new TransactionIncomeDeposit(),
                oldItem = new TransactionIncomeDeposit();
        if (id != null) {
            item = transactionIncomeDepositRepo.findById(id).orElseThrow();
            oldItem = (TransactionIncomeDeposit) item.clone();
            oldItem.setIncome((Income) item.getIncome().clone());
            oldItem.setDeposit((Deposit) item.getDeposit().clone());
        }

        item.setIncome(incomeRepo.findById(Long.valueOf(model.get("selectedIncome"))).orElseThrow());
        item.setDeposit(depositRepo.findById(Long.valueOf(model.get("selectedDeposit"))).orElseThrow());

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
            transactionIncomeDepositService.transactionSave(item);
        } else {
            transactionIncomeDepositService.transactionEdit(item, oldItem);
        }
        return "redirect:/transaction_income_deposit";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        TransactionIncomeDeposit transactionIncomeDeposit = transactionIncomeDepositRepo.findById(id).orElseThrow();
        transactionIncomeDepositService.transactionDelete(transactionIncomeDeposit);
        return "redirect:/transaction_income_deposit";
    }

}
