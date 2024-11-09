package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
//like stateless and statefull
public class DAO {
  @Autowired
  //like ejb
  //this will craete a child class so that a object is crated for that interface
  //like ep there will no implement class to do database operation that class with methods will be created automatically
  UserInterface repo;
  
  public void insert(User u1) {
    repo.save(u1);
  }
  
  public User findUser(String email) {
    return repo.findByEmail(email);
//    variable only with first letter captial it writes its own code retrive
  }
  public List<User> find(){
    return repo.findAll();
  }
  //import from .data packag
  public List<User> findPage(int page,int limit ){
    Sort sort=Sort.by(Sort.Direction.DESC,"name");
    Pageable pageable=PageRequest.of(page, limit,sort);
    return repo.findAll(pageable).toList();
  }
  
  public String deleteUser(String email) {
    repo.delete(repo.findByEmail(email));
    return "deleted"+email;
  }
  
public String editUser(User user) {
    deleteUser(user.getEmail());
    insert(user);
    return "Edited Successfully ";
  }
  //no update so delete and save
}