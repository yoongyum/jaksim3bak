package com.jaksim3.bak.domain.interested_product;

import com.jaksim3.bak.domain.interested.Interested;
import com.jaksim3.bak.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterestedProductRepository extends JpaRepository<InterestedProduct,Long> {

    List<InterestedProduct> findAllByInterested(Interested interested);

    Optional<InterestedProduct> findByProductAndInterested(Product product, Interested interested);
}
