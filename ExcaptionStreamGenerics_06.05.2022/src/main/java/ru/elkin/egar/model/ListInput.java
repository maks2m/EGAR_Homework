package ru.elkin.egar.model;

import java.util.ArrayList;
import java.util.List;

public class ListInput implements ListInputInterface<String> {

    private final List<String> listInput = new ArrayList<>();

    @Override
    public void addNewElement(String newElement) {
        listInput.add(newElement);
    }

    @Override
    public List<String> getList() {
        return listInput;
    }

    @Override
    public String getByIndex(int index) {
        return listInput.get(index);
    }
}
