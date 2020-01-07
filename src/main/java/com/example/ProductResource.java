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
public class ProductResource {
  
  @Autowired
  private ProductRepository repository;
  
  public ProductResource(ProductRepository repository) {
    this.repository = repository;
  }
  @RequestMapping(value = "/produtos/", method = RequestMethod.GET)
  public Iterable<Product> buscarProdutos(@RequestParam(required = false) String camiseta) {
    return this.repository.findAll();
  }
  @RequestMapping(value = "/produtos/{id}", method = RequestMethod.GET)
  public Optional<Product> buscarProduto(@PathVariable Long id) {
    return this.repository.findById(id);
  }
  @RequestMapping(value = "/produtos/{id}", method = RequestMethod.DELETE)
  public void removerProduto(@PathVariable Long id) {
    this.repository.deleteById(id);
  }
  @RequestMapping(value = "/produtos/", 
  method = RequestMethod.POST)
  public Product criarProduto(@RequestBody Product product) {
    String camiseta = product.getCamiseta();
    String tamanho = product.getTamanho();
    double valor = product.getValor();
    int quantidade = product.getQuantidade();
    return this.repository.save(new Product(camiseta, tamanho, quantidade, valor));
  }
  @RequestMapping(value="/produtos/{id}", 
  method=RequestMethod.PUT)
  public void alterarProduto(@PathVariable Long id,
  @RequestBody Product produtoParam) {
      Product produto = this.repository.findById(id).get();
      produto.setCamiseta(produtoParam.getCamiseta());
      produto.setTamanho(produtoParam.getTamanho());
      produto.setValor(produtoParam.getValor());
      produto.setQuantidade(produtoParam.getQuantidade());
  }
}
