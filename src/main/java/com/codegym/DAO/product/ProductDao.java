package com.codegym.DAO.product;

import com.codegym.connection.SingletonConnection;
import com.codegym.model.Category;
import com.codegym.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao{


    public static final String SELECT_PRODUCT = "select name_product,price,quantity,color,description,name_category from product p join productmanager.category c on c.id_category = p.id_category;";
    public static final String INSERT_PRODUCT = "insert into product (id_product,name_product,price,quantity,color,description) values (?,?,?,?,?,?);";

    @Override
    public List<Product> findAll() {
        List<Product> productList=new ArrayList<>();
        Connection connection=SingletonConnection.getConnection();
        try(
                PreparedStatement preparedStatement=connection.prepareStatement(SELECT_PRODUCT);
                ){
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int id_category=resultSet.getInt("id_category");
                int id_product=resultSet.getInt("id_product");
                String nam_category=resultSet.getString("name_category");
                String name_product=resultSet.getString("name_product");
                Double price=resultSet.getDouble("price");
                int quantity=resultSet.getInt("quantity");
                String color=resultSet.getString("color");
                String description=resultSet.getString("description");
                Category category=new Category(id_category,nam_category);
                Product product=new Product(id_product,name_product,price,quantity,color,description,category);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean save(Product product) {
        Connection connection=SingletonConnection.getConnection();
        try(
                PreparedStatement preparedStatement= connection.prepareStatement(INSERT_PRODUCT);
        ) {
            preparedStatement.setString(1,product.getName_product());
            preparedStatement.setInt(2,product.getQuantity());
            preparedStatement.setDouble(3,product.getPrice());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setString(6,product.getCategory().getId_category());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        return false;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }
}
