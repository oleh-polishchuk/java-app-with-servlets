package com.geekhub;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/books")
public class BookPageServlet extends HttpServlet {

    public Map<String, List> books = new HashMap<>();

    public void init() throws ServletException {
        Map<String, String> book = new HashMap<>();

        book.put("name", "Steve Jobs");
        book.put("message", "Hardcover – October 24, 2011");
        book.put("rating", "5");

        saveBookBySessionId("12345", book);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getRequestedSessionId();

        request.setAttribute("books", getBooksBySessionId(sessionId));
        request.getRequestDispatcher("/WEB-INF/book/books.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getRequestedSessionId();

        Map<String, String> book = new HashMap<>();
        book.put("createdAt", (new Date()).toString());
        book.put("name", request.getParameter("name"));
        book.put("message", request.getParameter("message"));
        book.put("rating", request.getParameter("rating"));
        saveBookBySessionId(sessionId, book);

        response.sendRedirect("/books");
    }

    /**
     * Save new book to local HashMap.
     * As key method use user session id
     * @param sessionId User session id
     * @param book Create book
     */
    public void saveBookBySessionId(String sessionId, Map book) {
        System.out.println("<== Save book with Session ID = " + sessionId);

        List list = books.get(sessionId);
        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(book);
        sortListDescending(list);

        books.put(sessionId, list);
    }

    /**
     * Returns a list of books by session id
     * @param sessionId User session id
     * @return List of books
     */
    public List getBooksBySessionId(String sessionId) {
        System.out.println("==> Get books with Session ID = " + sessionId);
        return books.get(sessionId);
    }

    /**
     * Sort list by field "createdAt" in descending order
     * @param list  list of books
     */
    public void sortListDescending(List list) {
        Collections.sort(list, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> previousVal, Map<String, String> nextVal) {
                return nextVal.get("createdAt").compareTo(previousVal.get("createdAt"));
            }
        });
    }
}
