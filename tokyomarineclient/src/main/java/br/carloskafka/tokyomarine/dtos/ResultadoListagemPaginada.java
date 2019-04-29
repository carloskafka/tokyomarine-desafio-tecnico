package br.carloskafka.tokyomarine.dtos;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultadoListagemPaginada {
	private int paginaAtual;
	private List<Integer> quantidadeTotalPaginas;
	private int quantidadeTotalPorPagina;
	
	public Integer getUltimaPagina() {
		return getQuantidadeTotalPaginas().size() > 0
				? getQuantidadeTotalPaginas().get(getQuantidadeTotalPaginas().size() - 1)
				: 0;
	}

	public List<Integer> getQuantidadeTotalPaginas() {
		return quantidadeTotalPaginas;
	}

	public void setQuantidadeTotalPaginas(int quantidadeTotalPaginas) {
		this.quantidadeTotalPaginas = quantidadeTotalPaginas > 0
				? IntStream.rangeClosed(1, quantidadeTotalPaginas).boxed().collect(Collectors.toList())
				: Collections.emptyList();
		Collections.sort(getQuantidadeTotalPaginas());
	}

	public int getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(int paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

	public void setQuantidadeTotalPaginas(List<Integer> quantidadeTotalPaginas) {
		this.quantidadeTotalPaginas = quantidadeTotalPaginas;
	}

	public int getQuantidadeTotalPorPagina() {
		return quantidadeTotalPorPagina;
	}

	public void setQuantidadeTotalPorPagina(int quantidadeTotalPorPagina) {
		this.quantidadeTotalPorPagina = quantidadeTotalPorPagina;
	}
}
