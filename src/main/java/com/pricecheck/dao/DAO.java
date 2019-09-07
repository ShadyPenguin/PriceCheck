package com.pricecheck.dao;

import java.util.List;

public interface DAO<T> {
  List<? extends T> getAll();
}
