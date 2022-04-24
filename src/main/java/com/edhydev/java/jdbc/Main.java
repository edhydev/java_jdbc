package com.edhydev.java.jdbc;

import com.edhydev.java.jdbc.models.Category;
import com.edhydev.java.jdbc.models.Product;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.repository.impl.ProductRepositoryImpl;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Repository<Product> repository = new ProductRepositoryImpl();

        System.out.println("========== FIND ALL ==========");
        repository.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("========== FIND BY ID ==========");
        System.out.println(repository.findById(1L));
        System.out.println();

        System.out.println("========== SAVE - CREAR ==========");
        Product save = new Product("coca cola 600ml", 16.5, new Date());
        Category category = new Category();
        category.setId(1L);
        save.setCategory(category);
        repository.save(save);
        System.out.println();

        System.out.println("========== SAVE - ACTUALIZAR ==========");
        Product p = repository.findById(1L);
        p.setName("coca cola 2.5L");
        repository.save(p);
        System.out.println();

        System.out.println("========== FIND ALL ==========");
        repository.findAll().forEach(System.out::println);
        System.out.println();
    }
}
