package com.example.rest.jpa.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.rest.jpa.demo.model.Topic;

public interface TopicPagingRepository extends PagingAndSortingRepository<Topic, Integer> {

}
