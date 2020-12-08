package com.southernartsoap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.southernartsoap.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

}
