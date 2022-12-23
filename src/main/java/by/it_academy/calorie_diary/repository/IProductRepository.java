package by.it_academy.calorie_diary.repository;

import by.it_academy.calorie_diary.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, UUID> {
}
