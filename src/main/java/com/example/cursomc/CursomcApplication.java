package com.example.cursomc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.domain.Estado;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.enums.TipoCliente;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.CidadeRepository;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.repositories.EstadoRepository;
import com.example.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository cr;
	@Autowired
	private ProdutoRepository pr;
	@Autowired
	private EstadoRepository er;
	@Autowired
	private CidadeRepository cidRep;
	@Autowired
	private ClienteRepository cliRep;
	@Autowired
	private EnderecoRepository endRep;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().add(cat1);
		
		cr.saveAll(Arrays.asList(cat1,cat2));
		pr.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", e1);
		Cidade c2 = new Cidade(null, "Sao Caetano do Sul", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().add(c1);
		e2.getCidades().addAll(Arrays.asList(c2,c3));
		
		er.saveAll(Arrays.asList(e1,e2));
		cidRep.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Gustavo", "gu@gmail.com", "41460930827", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("42294854","970214224"));
		
		Endereco end1 = new Endereco(null, "Rua nazaret", "156", null, "Barcelona", "09551070", cli1, c2);
		Endereco end2 = new Endereco(null, "Rua bandeirante", "974", null, "Santa Paula", "09551090", cli1, c3);

		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		cliRep.save(cli1);
		endRep.saveAll(Arrays.asList(end1,end2));
	}

}
