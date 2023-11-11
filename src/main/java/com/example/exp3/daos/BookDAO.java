package com.example.exp3.daos;

import com.example.exp3.models.entity.Book;
import com.example.exp3.utils.PageRequestUtil;

import java.util.List;


public interface BookDAO {
    //增加（保存）书信息
    public int save(Book book);

    //删除书信息(根据ID删除)
    public int deleteById(int id);

    //修改书信息(根据ID修改)
    public int updateById(int id, Book book);

    //查询书信息(根据ID查询)
    public Book queryById(int id);

    //查询分页总条数
    public int queryCountForPage(PageRequestUtil pageRequestUtil);

    //查询分页数据
    public List<Book> queryListForPage(PageRequestUtil pageRequestUtil);
}
