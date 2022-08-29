package com.jaksim3.bak.domain.interested;

import com.jaksim3.bak.domain.interested_product.InterestedProduct;
import com.jaksim3.bak.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Table(name = "INTERESTED")
@Entity
public class Interested {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "interested")
    private List<InterestedProduct> interestedProductList = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addInterestedProduct(InterestedProduct interestedProduct) {
        this.interestedProductList.add(interestedProduct);
        if(interestedProduct.getInterested() != this) {
            interestedProduct.setInterested(this);
        }
    }
}
