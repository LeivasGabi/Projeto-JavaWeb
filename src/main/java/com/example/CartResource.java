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
public class CartResource {
  
  @Autowired
  private CartRepository repository;
  
  public CartResource(ProductRepository repository) {
    this.repository = repository;
  }

  /**
   * Metodo de requisicao do tipo GET, para uma lista
   * @param raca tipo de raca para filtrar
   * @return lista de produtos, filtrados ou nao
   */
  @RequestMapping(value = "/cart/", method = RequestMethod.GET)
  public Iterable<Product> buscarProdutos(@RequestParam(required = false) String raca) {
    return this.repository.findAll();
  }

  /**
   * Metodo de requisicao do tipo GET, para um item
   * @param id identificador ou indice da colecao dos produtos
   * @return item de produto unico
   */
  @RequestMapping(value = "/produtos/{id}", method = RequestMethod.GET)
  public Optional<Product> buscarProduto(@PathVariable Long id) {
    return this.repository.findById(id);
    // return this.produtos.get(id - 1);
  }
  
  /**
   * Metodo de requisicao do tipo DELETE, para remover um item
   * @param id identificador ou indice da colecao dos produtos
   */
  @RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
  public void removerCart(@PathVariable Long id) {
    this.repository.deleteById(id);
    // this.produtos.remove(id - 1);
  }

  @RequestMapping(value = "/cart/", 
  method = RequestMethod.POST)
  public Product criarProduto(@RequestBody Product product) {
    String raca = product.getCamiseta();
    double valor = product.getValor();
    int quantidade = product.getQuantidade();
    String tamanho = product.getTamanho();
    return this.repository.save(new Product(camiseta, valor, quantidade, tamanho));
    // return new Product(raca, valor, quantidade);
  }

  @RequestMapping(value="/cart/{id}", method=RequestMethod.PUT)
  public void alterarCart(@PathVariable Long id,
  @RequestBody Product produtoParam) {
      Product produto = this.repository.findById(id).get();
      produto.setQuantidade(produtoParam.getQuantidade());
      produto.setValor(produtoParam.getValor());
      produto.setCamiseta(produtoParam.getCamiseta());
  }
}
