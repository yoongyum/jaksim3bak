package com.jaksim3.bak.domain.product;

import com.jaksim3.bak.domain.enums.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByAgeAndJob(int age, String job);
    List<Product> findByAgeAndJobAndInstitutionContainingIgnoreCase(int age, String job, String institution);

    List<Product> findByAgeAndJobAndLoanIsLessThanEqual(int age, String job, Long loan);
}
