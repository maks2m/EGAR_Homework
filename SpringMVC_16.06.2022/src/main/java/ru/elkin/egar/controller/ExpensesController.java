package ru.elkin.egar.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.egar.entity.Expenses;
import ru.elkin.egar.repo.ExpensesRepo;

@Controller
@RequestMapping("/expenses")
public class ExpensesController implements EntityControllerInterface<Expenses> {

    private final ExpensesRepo expensesRepo;

    @Autowired
    public ExpensesController(ExpensesRepo expensesRepo) {
        this.expensesRepo = expensesRepo;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        model.addAttribute("expensesList", expensesRepo.findAllByOrderById());
        return "expenses";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("expenses", new Expenses());
        return "expenses_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Expenses expenses = expensesRepo.findById(id).orElseThrow();
        model.addAttribute("expenses", expenses);
        return "expenses_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("expenses") Expenses item) {
        Expenses itemFromDB;
        if (id == null) {
            itemFromDB = new Expenses();
        } else {
            itemFromDB = expensesRepo.findById(id).orElseThrow();
        }

        BeanUtils.copyProperties(item, itemFromDB, "id");
        expensesRepo.save(itemFromDB);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        expensesRepo.deleteById(id);
        return "redirect:/";
    }
}
