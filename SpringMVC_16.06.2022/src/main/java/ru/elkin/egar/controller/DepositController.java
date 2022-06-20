package ru.elkin.egar.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.egar.entity.Deposit;
import ru.elkin.egar.repo.DepositRepo;

@Controller
@RequestMapping("/deposit")
public class DepositController implements EntityControllerInterface<Deposit> {

    private final DepositRepo depositRepo;

    @Autowired
    public DepositController(DepositRepo depositRepo) {
        this.depositRepo = depositRepo;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        return "deposit";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("deposit", new Deposit());
        return "deposit_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Deposit deposit = depositRepo.findById(id).orElse(null);
        model.addAttribute("deposit", deposit);
        return "deposit_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("deposit") Deposit item) {

        Deposit itemFromDB;
        if (id == null) {
            itemFromDB = new Deposit();
        } else {
            itemFromDB = depositRepo.findById(id).orElseThrow();
        }

        BeanUtils.copyProperties(item, itemFromDB, "id");
        depositRepo.save(itemFromDB);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        depositRepo.deleteById(id);
        return "redirect:/";
    }

}
