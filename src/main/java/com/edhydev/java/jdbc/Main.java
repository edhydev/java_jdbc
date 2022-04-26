package com.edhydev.java.jdbc;

import com.edhydev.java.jdbc.models.Category;
import com.edhydev.java.jdbc.models.Product;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.repository.impl.CategoryRepositoryImpl;
import com.edhydev.java.jdbc.repository.impl.ProductRepositoryImpl;

import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Repository<Product> productRepository;
    private static Repository<Category> categoryRepository;

    public static void main(String[] args) {

        productRepository = new ProductRepositoryImpl();
        categoryRepository = new CategoryRepositoryImpl();

        StringBuilder sb = new StringBuilder("Seleccione una opción:\n");
        sb.append("1. Agregar\n");
        sb.append("2. Actualizar \n");
        sb.append("3. Eliminar\n");
        sb.append("4. Listar\n");
        sb.append("5. Salir");

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        try {
            int opcion = 0;
            do {
                System.out.println(sb);
                System.out.print("Opcion: ");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        create(scanner);
                        break;
                    case 2:
                        update(scanner);
                        break;
                    case 3:
                        delete(scanner);
                        break;
                    case 4:
                        productRepository.findAll().forEach(System.out::println);
                        break;
                    default:
                        if (opcion <= 0 || opcion > 5) {
                            throw new Exception("Opción no válida");
                        }
                        break;
                }
            } while (opcion < 5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            main(args);
        }
    }

    private static void create(Scanner scanner) throws Exception {
        Product p = new Product();
        p.setCategory(new Category());
        p.setRegisterDate(new Date());

        System.out.print("Nombre: ");
        try {
            p.setName(scanner.next());
            if (p.getName().isEmpty()) throw new Exception("El nombre es requerido");
        } catch (Exception e) {
            throw new Exception("El nombre es requerido");
        }

        System.out.print("Precio: ");
        try {
            p.setPrice(scanner.nextDouble());
        } catch (Exception e) {
            throw new Exception("El precio es requerido");
        }

        System.out.print("Sku: ");
        try {
            p.setSku(scanner.next());
            if (p.getSku().isEmpty()) throw new Exception("El sku es requerido");
        } catch (Exception e) {
            throw new Exception("El sku es requerido");
        }

        printCategories();

        System.out.print("Categoría: ");
        try {
            p.getCategory().setId(scanner.nextLong());
        } catch (Exception e) {
            throw new Exception("El ID Categoria es requerido");
        }

        productRepository.save(p);
    }

    private static void update(Scanner scanner) throws Exception {
        System.out.print("ID: ");
        Long productId = scanner.nextLong();

        Product p2 = productRepository.findById(productId);

        if (p2 == null) throw new Exception("Producto no encontrado");

        System.out.print("Nombre(" + p2.getName() + "): ");
        try {
            p2.setName(scanner.next());
        } catch (Exception ignored) {
        }

        System.out.print("Precio(" + p2.getPrice() + "): ");
        try {
            p2.setPrice(scanner.nextDouble());
        } catch (Exception ignored) {
        }

        System.out.print("Sku(" + p2.getSku() + "): ");
        try {
            p2.setSku(scanner.next());
        } catch (Exception ignored) {
        }

        printCategories();

        System.out.print("ID Categoría(" + p2.getCategory().getId() + "): ");
        try {
            p2.getCategory().setId(scanner.nextLong());
        } catch (Exception ignored) {
        }

        productRepository.save(p2);
    }

    private static void delete(Scanner scanner) {
        System.out.print("ID del producto:");
        Long id = scanner.nextLong();
        productRepository.delete(id);
        System.out.println("Producto eliminado correctamente");
    }

    private static void printCategories() {
        System.out.println();
        System.out.println("******* Categorías *******");
        categoryRepository.findAll().forEach(System.out::println);
        System.out.println();
    }
}
