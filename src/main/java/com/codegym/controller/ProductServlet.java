package com.codegym.controller;

import com.codegym.DAO.category.CategoryDao;
import com.codegym.DAO.category.ICategoryDao;
import com.codegym.DAO.product.IProductDao;
import com.codegym.DAO.product.ProductDao;
import com.codegym.model.Category;
import com.codegym.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

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
                showFormEdit(request,response);
                break;
            case "delete":
                showFormDelete(request,response);
                break;
            case "search":
                break;
            default:
                listProduct(request, response);
                break;
        }

    }

    private void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/delete.jsp");
        request.setAttribute("product",productDao.findAll());
        request.setAttribute("category", categoryDao.findAll());
        dispatcher.forward(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");
        request.setAttribute("product",productDao.findAll());
        request.setAttribute("category", categoryDao.findAll());
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        request.setAttribute("category", categoryDao.findAll());
        dispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList=productDao.findAll();
        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/list.jsp");
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);

                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "search":

                break;

        }

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDao productDao1 =new ProductDao();
        productDao1.deleteProduct(id);


        List<Product> productList = productDao.findAll();
        request.setAttribute("product", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/delete.jsp");
        dispatcher.forward(request, response);

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));

        String name=request.getParameter("name");
        String color=request.getParameter("color");
        String description=request.getParameter("description");
        double price=Double.valueOf(request.getParameter("price"));
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        String idString=request.getParameter("category");
        int idCategory=Integer.parseInt(idString);
        Category category=categoryDao.findById(idCategory);
        Product product=new Product(id,name,price,quantity,color,description,category);
        productDao.update(product);
        RequestDispatcher dispatcher=request.getRequestDispatcher("view/edit.jsp");
        request.setAttribute("product",productDao.findAll());
        request.setAttribute("category",categoryDao.findAll());
        dispatcher.forward(request,response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name_product= request.getParameter("name");
        double price=Double.valueOf(request.getParameter("price"));
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        String color=request.getParameter("color");
        String description=request.getParameter("description");
        String idString =request.getParameter("category");
        int id_category=Integer.parseInt(idString);
        Category category = categoryDao.findById(id_category);
        Product product=new Product(name_product,price,quantity,color,description,category);
        productDao.save(product);
        request.setAttribute("product",productDao.findAll());
        RequestDispatcher dispatcher=request.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(request,response);
    }
}
