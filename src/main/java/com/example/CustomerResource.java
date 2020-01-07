package com.example;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CustomerResource {
  
  @Autowired
  private CustomerRepository repository;
  
  public CustomerResource(CustomerRepository repository) {
    this.repository = repository;
  }

  @RequestMapping(value = "/customer/", method = RequestMethod.GET)
  public Iterable<Customer> buscarCustumer(@RequestParam(required = false) String firstName) {
    return this.repository.findAll();
  }
  @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
  public Optional<Customer> buscarCustomer(@PathVariable Long id) {
    return this.repository.findById(id);
  }
  
  @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
  public void removerCustomer(@PathVariable Long id) {
    this.repository.deleteById(id);
  }
  @RequestMapping(value = "/customer/", method = RequestMethod.POST)
  public Customer criarCustomer(@RequestBody Customer customer) {
    String firstName = customer.getFirstName();
    String lastName = customer.getLastName();
    return this.repository.save(new Customer(firstName, lastName));
  }
  @RequestMapping(value="/customer/{id}", method=RequestMethod.PUT)
  public void alterarCustomer(@PathVariable Long id,
  @RequestBody Customer customerParam) {
      Customer customer = this.repository.findById(id).get();
      customer.setFirstName(customerParam.getFirstName());
      customer.setLastName(customerParam.getLastName());
  }
}
