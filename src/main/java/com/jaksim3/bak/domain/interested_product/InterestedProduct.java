package com.jaksim3.bak.domain.interested_product;

import com.jaksim3.bak.domain.basetime.BaseTimeEntity;
import com.jaksim3.bak.domain.interested.Interested;
import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name ="INTERESTED_PRODUCT")
@Entity
public class InterestedProduct extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interested_id")
    private Interested interested;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public InterestedProduct(Interested interested, Product product) {
        this.interested = interested;
        this.product = product;
    }

    public void setInterested(Interested interested) {
        if(this.interested != null){
            this.interested.getInterestedProductList().remove(this);
        }
        this.interested = interested;
        if(!this.interested.getInterestedProductList().contains(this)){
            this.interested.addInterestedProduct(this);
        }
    }

    public void setProduct(Product product) {
        if(this.product != null){
            this.product.getInterestedProductList().remove(this);
        }
        this.product = product;
        if(!this.product.getInterestedProductList().contains(this)){
            this.product.addInterestedProduct(this);
        }
    }
}
