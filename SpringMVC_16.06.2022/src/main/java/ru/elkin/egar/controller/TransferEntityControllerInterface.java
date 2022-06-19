package ru.elkin.egar.controller;

import org.springframework.ui.Model;

import java.util.Map;

public interface TransferEntityControllerInterface {
    String list(Model model);
    String create(Model model);
    String edit(Long id,
                Model model);
    String save(Long id,
                Map<String, String> model) throws CloneNotSupportedException;
    String delete(Long id);

}
