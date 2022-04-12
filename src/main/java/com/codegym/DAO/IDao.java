package com.codegym.DAO;

import java.util.List;

public interface IDao <T>{
    List<T> findAll();
    T findById(int id);
    boolean update(T t);
    boolean save(T t);
    boolean delete(T t);
}
