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

    public static final String FIND_ALL_PRODUCT = "select id_product,name_product,price,quantity,color,description,c.id_category,name_category as category from product p join category c on c.id_category = p.id_category;";
    public static final String INSERT_PRODUCT = "insert into product (name_product,price,quantity,color,description,id_category) values (?,?,?,?,?,?);";
    public static final String FIND_BY_ID = "select id_product,name_product,price,quantity,color,description as category from product p join category c on c.id_category = p.id_category where id_product=?";

    @Override
    public List<Product> findAll() {
        List<Product> productList=new ArrayList<>();
        Connection connection=SingletonConnection.getConnection();
        try(
                PreparedStatement preparedStatement=connection.prepareStatement(FIND_ALL_PRODUCT);
                ){
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int id_product=resultSet.getInt("id_product");
                int id_category=resultSet.getInt("c.id_category");
                String name_category=resultSet.getString("category");
                String name_product=resultSet.getString("name_product");
                Double price=resultSet.getDouble("price");
                int quantity=resultSet.getInt("quantity");
                String color=resultSet.getString("color");
                String description=resultSet.getString("description");
                Category category=new Category(id_category,name_category);
                Product product=new Product(id_product,name_product,price,quantity,color,description,category);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static void main(String[] args) {
        IProductDao productDao =new ProductDao();
        System.out.println(productDao.findAll());
    }

    @Override
    public Product findById(int id_product) {
        Connection connection =SingletonConnection.getConnection();
        Product product=null;
        try(
                PreparedStatement preparedStatement=connection.prepareStatement(FIND_BY_ID);
                ) {
            preparedStatement.setInt(1,id_product);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String name_product =resultSet.getString("name_product");
                Double price =resultSet.getDouble("price");
                int quantity =resultSet.getInt("quantity");
                String color =resultSet.getString("color");
                String description =resultSet.getString("description");
                String name_category=resultSet.getString("name_category");
                Category category=new Category(name_category);
                product=new Product(id_product,name_product,price,quantity,color,description,category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
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
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setInt(6,product.getCategory().getId_category());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int id){
        boolean rowDeleted;
        try (Connection connection = SingletonConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("delete from product where id_product=?");) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }
}
