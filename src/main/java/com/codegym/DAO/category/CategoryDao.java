package com.codegym.DAO.category;

import com.codegym.connection.SingletonConnection;
import com.codegym.model.Category;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategoryDao{

    @Override
        public List<Category> findAll() {
            List<Category> categoryList=new ArrayList<>();
            Connection connection= SingletonConnection.getConnection();
            try (
                    PreparedStatement preparedStatement= connection.prepareStatement("select * from category");
            )
            {
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    int id_category =resultSet.getInt("id_category");
                    String name_category=resultSet.getString("name_category");
                    Category category=new Category(id_category,name_category);
                    categoryList.add(category);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return categoryList;
    }

    @Override
    public Category findById(int id_product) {
        Connection connection=SingletonConnection.getConnection();
        Category category =null;
        try(
                PreparedStatement preparedStatement=connection.prepareStatement("selec*from catagory where id_category=?");
                ){
                    preparedStatement.setInt(1,id_product);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    while (resultSet.next()){
                        String name_category=resultSet.getString("name_category");
                        category=new Category(id_product,name_category);
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public boolean save(Category category) {

        return false;
    }

    @Override
    public boolean delete(Category category) {
        return false;
    }
}
