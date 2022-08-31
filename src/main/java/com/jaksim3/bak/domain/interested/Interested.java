package com.jaksim3.bak.domain.interested;

import com.jaksim3.bak.domain.interested_product.InterestedProduct;
import com.jaksim3.bak.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "INTERESTED")
@Entity
public class Interested {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "interested")
    private List<InterestedProduct> interestedProducts = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addInterestedProduct(InterestedProduct interestedProduct) {
        this.interestedProducts.add(interestedProduct);
        if(interestedProduct.getInterested() != this) {
            interestedProduct.setInterested(this);
        }
    }
}
