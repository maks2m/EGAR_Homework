package ru.elkin.egar.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.egar.entity.Income;
import ru.elkin.egar.repo.IncomeRepo;

@Controller
@RequestMapping("/income")
public class IncomeController implements EntityControllerInterface<Income> {

    private final IncomeRepo incomeRepo;

    @Autowired
    public IncomeController(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        model.addAttribute("incomeList", incomeRepo.findAllByOrderById());
        return "income";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("income", new Income());
        return "income_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Income income = incomeRepo.findById(id).orElse(null);
        model.addAttribute("income", income);
        return "income_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("income") Income item) {
        Income itemFromDB;
        if (id == null) {
            itemFromDB = new Income();
        } else {
            itemFromDB = incomeRepo.findById(id).orElseThrow();
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        incomeRepo.save(itemFromDB);
        return "redirect:/income";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        incomeRepo.deleteById(id);
        return "redirect:/income";
    }

}
