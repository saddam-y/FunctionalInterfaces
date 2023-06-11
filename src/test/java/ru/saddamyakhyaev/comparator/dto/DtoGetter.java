package ru.saddamyakhyaev.comparator.dto;

import java.util.List;

public interface DtoGetter <T> {
    List<T> list();
    List<T> listWithNullElements();
}
