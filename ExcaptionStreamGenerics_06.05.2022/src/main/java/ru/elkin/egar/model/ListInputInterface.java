package ru.elkin.egar.model;

import java.util.List;

public interface ListInputInterface<T> {

    void addNewElement(T newElement);
    List<T> getList();
    T getByIndex(int index);

}
