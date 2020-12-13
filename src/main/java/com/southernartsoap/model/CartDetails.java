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

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_details_id") //correct name?
    private Long id;
    
    //product id witt ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, optional = false) //shouldl this be optinal?
    @JoinColumn(name = "product_id", nullable = false)//should this be nullable? //does this join to join column
    private Product product;
    
    //cart ID with ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cart cart;
    
    @Min(0)
    private int quantity;
    
    private String comments;
    //any product can have any scent and color and they are determined at checkout which is why we are storing them in the cart
    //soaps are made on demand.
    private String scent;
    private String color; 
    
}
