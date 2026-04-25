package com.backend.dao;

import com.backend.model.BaseModel;
import java.util.List;

//This class acts as the DAO layer that access the database and performs 
//CRUD operations.

public class GenericDAO<T extends BaseModel> {

    private final List<T> items;

    public GenericDAO(List<T> items) {
        this.items = items;
    }

    public List<T> getAll() {
        return items;
    }

    public T getById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public T add(T item) {
        if (getById(item.getId()) != null) {
            throw new IllegalArgumentException("Id already exists");
        }

        items.add(item);
        return item;
    }

    public void update(T updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (item.getId().equals(updatedItem.getId())) {
                items.set(i, updatedItem);
                return;
            }
        }
    }

    public void delete(String id) {
        items.removeIf(item -> item.getId().equals(id));
    }
}
