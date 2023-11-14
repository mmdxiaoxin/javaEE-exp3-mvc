package com.example.exp3.daos;

import com.example.exp3.models.entity.Book;

import java.util.List;


public interface BookDAO {
    //返回全部书信息
    List<Book> list();

    //增加（保存）书信息
    int save(Book book);

    //删除书信息(根据ID删除)
    int deleteById(int id);

    //修改书信息(根据ID修改)
    int updateById(int id, Book book);

    //查询书信息(根据ID查询)
    Book queryById(int id);
}
