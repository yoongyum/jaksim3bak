package com.jaksim3.bak.domain.product;

import com.jaksim3.bak.domain.enums.Job;
import com.jaksim3.bak.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByAgeAndJob(int age, String job);

    @Query("SELECT p FROM Product p WHERE p.age = :age AND p.job = :job AND p.loan <= :loan")
    List<Product> findLoan(@Param("age") int age, @Param("job") String job, @Param("loan") Long loan);

    @Query("SELECT p FROM Product p WHERE p.institution LIKE  %:institution% AND p.age = :age AND p.job = :job AND p.loan <= :loan")
    List<Product> findInstitution(@Param("age") int age, @Param("job") String job, @Param("loan") Long loan, @Param("institution") String institution);

}
