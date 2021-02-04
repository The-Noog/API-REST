package com.os.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.os.domain.exception.EntidadeNaoEncontradaException;
import com.os.domain.exception.NegocioException;
import com.os.domain.model.Cliente;
import com.os.domain.model.Comentario;
import com.os.domain.model.OrdemServico;
import com.os.domain.model.StatusOrdemServico;
import com.os.domain.repository.ClienteRepository;
import com.os.domain.repository.ComentarioRepository;
import com.os.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));

		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());

		return ordemServicoRepository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoid) {
		OrdemServico ordemServico = buscar(ordemServicoid);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}		
	
	

	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
		.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);

		return comentarioRepository.save(comentario);
	}
	
	private OrdemServico buscar (Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
		.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		}

}
