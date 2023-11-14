package com.example.exp3.daos.impl;

import com.example.exp3.daos.BookDAO;
import com.example.exp3.models.entity.Book;
import com.example.exp3.utils.DateUtil;
import com.example.exp3.utils.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private Connection conn = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;

    @Override
    public List<Book> list() {
        List<Book> books = null;
        try {
            conn = DatabaseConnectionUtil.getConnection();
            books = new ArrayList<>();
            String sql = "select * from book";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setContent(rs.getString("content"));
                book.setName(rs.getString("name"));
                book.setPage(rs.getInt("page"));
                book.setPrice(rs.getFloat("price"));
                book.setPublish(rs.getString("publish"));
                book.setPublishDate(rs.getDate("publishDate"));
                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(conn, pre, rs);
        }
        return books;
    }

    //增加（保存）书信息
    @Override
    public int save(Book book) {
        try {
            conn = DatabaseConnectionUtil.getConnection();
            String sql = "insert into book(name,author,publish,publishdate,page,price,content)values(?,?,?,?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, book.getName());
            pre.setString(2, book.getAuthor());
            pre.setString(3, book.getPublish());
            pre.setDate(4, DateUtil.utilToSqlDate(book.getPublishDate()));
            pre.setInt(5, book.getPage());
            pre.setFloat(6, book.getPrice());
            pre.setString(7, book.getContent());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(conn, pre, rs);
        }
        return 0;
    }

    //删除书信息(根据ID删除)
    @Override
    public int deleteById(int id) {
        try {
            conn = DatabaseConnectionUtil.getConnection();
            String sql = "delete from book where id=?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(conn, pre, rs);
        }
        return 0;
    }

    //修改书信息(根据ID修改)
    @Override
    public int updateById(int id, Book book) {
        try {
            conn = DatabaseConnectionUtil.getConnection();
            String sql = "update book set name=?,author=?,publish=?,publishdate=?,page=?,price=?,content=? where id=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, book.getName());
            pre.setString(2, book.getAuthor());
            pre.setString(3, book.getPublish());
            pre.setDate(4, DateUtil.utilToSqlDate(book.getPublishDate()));
            pre.setInt(5, book.getPage());
            pre.setFloat(6, book.getPrice());
            pre.setString(7, book.getContent());
            pre.setInt(8, id);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(conn, pre, rs);
        }
        return 0;
    }

    //查询书信息(根据ID查询)
    @Override
    public Book queryById(int id) {
        try {
            conn = DatabaseConnectionUtil.getConnection();
            String sql = "select * from book where id=?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setContent(rs.getString("content"));
                book.setName(rs.getString("name"));
                book.setPage(rs.getInt("page"));
                book.setPrice(rs.getFloat("price"));
                book.setPublish(rs.getString("publish"));
                book.setPublishDate(rs.getDate("publishDate"));
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(conn, pre, rs);
        }
        return null;
    }
}
