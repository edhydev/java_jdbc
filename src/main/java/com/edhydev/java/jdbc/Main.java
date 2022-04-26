package com.edhydev.java.jdbc;

import com.edhydev.java.jdbc.models.Category;
import com.edhydev.java.jdbc.models.Product;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.repository.impl.ProductRepositoryImpl;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Repository<Product> repository = new ProductRepositoryImpl();

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
                        System.out.print("Nombre: ");
                        String name = scanner.next();

                        System.out.print("Precio: ");
                        Double price = scanner.nextDouble();

                        System.out.print("Sku: ");
                        String sku = scanner.next();

                        System.out.print("Categoría: ");
                        Long categoryId = scanner.nextLong();

                        Product p = new Product();
                        p.setName(name);
                        p.setPrice(price);
                        p.setSku(sku);
                        Category category = new Category();
                        category.setId(categoryId);
                        p.setCategory(category);
                        p.setRegisterDate(new Date());
                        repository.save(p);
                        break;
                    case 2:
                        System.out.print("ID: ");
                        Long productId = scanner.nextLong();

                        Product p2 = repository.findById(productId);

                        if (p2 == null) throw new Exception("Producto no encontrado");

                        System.out.print("Nombre(" + p2.getName() + "): ");
                        p2.setName(scanner.next());

                        System.out.print("Precio(" + p2.getPrice() + "): ");
                        p2.setPrice(scanner.nextDouble());

                        System.out.print("Sku(" + p2.getSku() + "): ");
                        p2.setSku(scanner.next());

                        System.out.print("ID Categoría(" + p2.getCategory().getId() + "): ");
                        p2.getCategory().setId(scanner.nextLong());

                        repository.save(p2);
                        break;
                    case 3:
                        System.out.print("ID del producto:");
                        Long id = scanner.nextLong();
                        repository.delete(id);
                        System.out.println("Producto eliminado correctamente");
                        break;
                    case 4:
                        repository.findAll().forEach(System.out::println);
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
}
