package com.example.exp3.service;

import com.example.exp3.daos.impl.BookDAOImpl;
import com.example.exp3.models.entity.Book;

import java.sql.SQLException;
import java.util.List;

public class BooksService {
    private BookDAOImpl bookDAO;

    public BooksService() {
        this.bookDAO = new BookDAOImpl();
    }

    // Get all books
    public List<Book> list() throws SQLException {
        return bookDAO.list();
    }

    // Get book by ID
    public Book getById(int id) throws SQLException {
        return bookDAO.queryById(id);
    }

    // Insert or update book
    public void save(Book book) throws SQLException {
        if (book.getId() == 0) {
            bookDAO.save(book);
        } else {
            bookDAO.updateById(book.getId(), book);
        }
    }

    // Delete book
    public void delete(int id) throws SQLException {
        bookDAO.deleteById(id);
    }
}
