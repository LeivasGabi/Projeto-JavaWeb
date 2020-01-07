package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String camiseta;
    private String tamanho;
    private int quantidade;
    private double valor;
    protected Product() {}
    public Product(String camiseta, String tamanho, int quantidade, double valor) {
        this.camiseta = camiseta;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.valor = valor;
    }
    public String getCamiseta() {
        return this.camiseta;
    }
    public void setCamiseta(String camiseta) {
        this.camiseta = camiseta;
    }

    public String getTamanho() {
        return this.tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    public int getQuantidade() {
        return this.quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }
	/**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
	public Long getId() {
		return id;
	}
}