package com.jaksim3.bak.domain.cart_product;

import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    List<CartProduct> findAllByCart(Cart cart);

    Optional<CartProduct> findByProductAndCart(Product product, Cart cart);
}
