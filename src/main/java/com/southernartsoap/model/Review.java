/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.model;

/**
 *
 * @author jared
 */
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "review_id")
        private Long id;
        
        //product id
        //one product has many reviews
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;
        //customer_id
        //one customer has many reviews
        
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //fetch crap
        @JoinColumn(name = "customer_id")
        private Customer customer;
        
        //content
        String content;
        //rating(stars)
        @Min(1)
        @Max(5)  
        int rating;
        //created_at timestamp
         @CreationTimestamp 
         private Date createdAt;
        //updates_at timestamp
         @UpdateTimestamp
         private Date updatedAt;
}
