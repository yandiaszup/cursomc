package com.example.cursomc;

import com.example.cursomc.domain.*;
import com.example.cursomc.enums.TipoCliente;
import com.example.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	public CategoriaRepository categoriaRepository;
	@Autowired
	public ProdutoRepository produtoRepository;
	@Autowired
	public CidadeRepository cidadeRepository;
	@Autowired
	public EstadoRepository estadoRepository;
	@Autowired
	public ClienteRepository clienteRepository;
	@Autowired
	public EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null,"INFORMATICA");
		Categoria cat2 = new Categoria(null,"ESCRITORIO");

		Produto p1 = new Produto(null,"Compupador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"Sao Paulo");

		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"Sao Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		Cliente cli1 = new Cliente(null,"Maria","maria@hotmail","23123123",1);
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","231233",cli1,c1);
		Endereco e2 = new Endereco(null,"AV Matos","78","Apto 401","Sta monica","38408",cli1,c2);

		cli1.getTelefones().addAll(Arrays.asList("123123","1123132"));
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}
