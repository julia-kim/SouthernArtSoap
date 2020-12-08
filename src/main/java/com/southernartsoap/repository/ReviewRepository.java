/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jared
 */
public interface ReviewRepository extends CrudRepository<Rewiew, Long>{
    List<Review> findAllReviewsByProductId();
}


//import java.util.List;
//
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import com.tts.TechTalentTwitter.model.Tweet;
//import com.tts.TechTalentTwitter.model.User;
//
//@Repository
//public interface TweetRepository extends CrudRepository<Tweet, Long> {
//    List<Tweet> findAllByOrderByCreatedAtDesc();
//    List<Tweet> findAllByUserOrderByCreatedAtDesc(User user);
//    List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<User> users);
//    List<Tweet> findByTags_PhraseOrderByCreatedAtDesc(String phrase);
//}
