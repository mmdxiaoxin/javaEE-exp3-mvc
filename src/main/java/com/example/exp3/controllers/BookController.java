package com.example.exp3.controllers;

import com.example.exp3.models.entity.Book;
import com.example.exp3.service.BooksService;
import com.example.exp3.utils.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookController", value = "/book-ctrl")
public class BookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BooksService booksService = new BooksService();
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                List<Book> books = booksService.list();
                System.out.println(books);
                request.setAttribute("result", books);
                request.getRequestDispatcher("/views/list.jsp").forward(request, response);
            } else if ("add".equals(action)) {
                request.getRequestDispatcher("/views/add.jsp").forward(request, response);
            } else if ("edit".equals(action)) {
                String id = request.getParameter("id");
                Book book = booksService.getById(Integer.parseInt(id));
                request.setAttribute("book", book);
                request.getRequestDispatcher("/views/edit.jsp").forward(request, response);
            } else if ("save".equals(action)) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String author = request.getParameter("author");
                String publish = request.getParameter("publish");
                String publishDate = request.getParameter("publishDate");
                String page = request.getParameter("page");
                String price = request.getParameter("price");
                String content = request.getParameter("content");
                Book book = new Book();
                book.setName(name);
                book.setAuthor(author);
                book.setPublish(publish);
                book.setPublishDate(DateUtil.strToUtilDate(publishDate));
                book.setPage(Integer.parseInt(page));
                book.setPrice(Float.parseFloat(price));
                book.setContent(content);
                if (id == null || id.isEmpty()) {
                    booksService.save(book);
                } else {
                    int idi = Integer.parseInt(id);
                    book.setId(idi);
                    booksService.save(book);
                }
                response.sendRedirect("book-ctrl?action=list");
            } else if ("delete".equals(action)) {
                String id = request.getParameter("id");
                int idi = Integer.parseInt(id);
                booksService.delete(idi);
                response.sendRedirect("book-ctrl?action=list");
            } else {
                throw new Exception("非法请求！！");
            }
        } catch (Exception e) {
            request.setAttribute("msg", e.getLocalizedMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
