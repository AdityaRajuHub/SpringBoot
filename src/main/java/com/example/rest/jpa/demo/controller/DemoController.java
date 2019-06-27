package com.example.rest.jpa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.jpa.demo.model.Topic;
import com.example.rest.jpa.demo.service.TopicService;

@RestController
//@RequestMapping("/demo")
public class DemoController {
	
	@Autowired
	TopicService topicService;
	
	@GetMapping(value="/topics")
	public List<Topic> getAllTopics(@AuthenticationPrincipal final UserDetails userDtls) {
		
		//System.out.println("User requesting the resources: "+userDtls.getUsername());
		//userDtls.getAuthorities().stream().forEach(t -> System.out.println(t));
		//perform rol based authorization here before sending out response
		
		return topicService.getAllTopics();
	}
	
	@GetMapping(value="/topics/{id}")
	public Topic getTopic(@PathVariable Integer id) {
		return topicService.getTopic(id);
	}
	
	@GetMapping(value="/topics/author/{author}")
	public List<Topic> getTopicByAuthor(@PathVariable String author) {
		return topicService.getTopicByAuthor(author);
	}
	
	@GetMapping(value="/topics/name/{name}")
	public List<Topic> getTopicByName(@PathVariable String name) {
		return topicService.getTopicByName(name);
	}
	
	@GetMapping(value="/topics/name/{name}/author/{author}")
	public List<Topic> getTopicByNameAndAuthor(@PathVariable String name, @PathVariable String author) {
		return topicService.findByAuthorAndName(name, author);
	}
	
	@GetMapping(value="/topics/page/{pageNo}")
	public List<Topic> getTopicsByPage(@PathVariable Integer pageNo) {
		return topicService.getTopicsByPage(pageNo);
	}
	
	@PostMapping(value="/topic")
	public void saveTopic(@RequestBody Topic topic) {
		topicService.saveTopic(topic);
	}
	
	@PostMapping(value="/topics")
	public void saveTopics(@RequestBody List<Topic> topics) {
		topicService.saveTopics(topics);
	}
	
	@PutMapping(value="/topics/{id}")
	public void updateTopic(@PathVariable Integer id, @RequestBody Topic topic) {
		topic.setId(id);
		topicService.updateTopic(topic);
	}
	
	@DeleteMapping(value="/topics/{id}")
	public void deleteTopic(@PathVariable Integer id) {
		topicService.deleteTopic(id);
	}
}
