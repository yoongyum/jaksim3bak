package com.jaksim3.bak.domain.cart_product;

import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    Optional<CartProduct> findByProduct(Product product);

    List<CartProduct> findAllByCart(Cart cart);


}
