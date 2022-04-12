package com.codegym.controller;

import com.codegym.DAO.category.CategoryDao;
import com.codegym.DAO.category.ICategoryDao;
import com.codegym.DAO.product.IProductDao;
import com.codegym.DAO.product.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private ICategoryDao categoryDao=new CategoryDao();
    private IProductDao productDao=new ProductDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                    showNewForm(request, response);
                break;
            case "edit":
//                    showEditForm(request, response);
                break;
            case "delete":
//                    ShowDeleteForm(request,response);
                break;
            case "search":
//                    ShowSearchForm(request,response);
                break;
            default:
                listProduct(request, response);
                break;
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("product",productDao.findAll());
        request.setAttribute("category",categoryDao.findAll());
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/list.jsp");
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
