package com.example.cursomc.services;

import com.example.cursomc.DTO.ClientNewDto;
import com.example.cursomc.DTO.ClienteDTO;
import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.repositories.CidadeRepository;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.servicesexceptions.DataIntegrityException;
import com.example.cursomc.servicesexceptions.ObjectNotFoundException;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ClienteRepository repo;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj =  repo.save(obj);
        enderecoRepository.saveAll(obj.getEndereco());
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());

    }

    public void delete(Integer id) { // Apenas deleta Clientes que nao tem produtos
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Nao e possivel excluir Cliente a Pedidos relacionados");
        }
    }

    public List<Cliente> findall() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null, null);
    }

    public Cliente fromDTO(ClientNewDto objDto){
        Cliente cli = new Cliente(null,objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(),objDto.getTipo(), bCryptPasswordEncoder.encode(objDto.getSenha()));
        Cidade cidade = new Cidade(objDto.getCidadeId(),null,null);
        Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli,cidade);
        cli.getEndereco().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!= null){
            cli.getTelefones().add(objDto.getTelefone2());
        }
        else if (objDto.getTelefone3()!= null){
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }
}
