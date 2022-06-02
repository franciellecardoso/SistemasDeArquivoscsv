package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FifaController implements IFifaController {

	public FifaController() {
		super();
	}

	@Override
	public Stack<String> empilhaBrasileiros(String caminho, String nome) throws IOException {
		Stack<String> pilha = new Stack<String>();
		File dir = new File(caminho);
		File arq = new File(caminho, nome);
		if (dir.exists() && dir.isDirectory()) {
			if (arq.exists()) {
				FileInputStream fluxo = new FileInputStream(arq);
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				buffer.readLine();

				while (linha != null) {
					int j = 0;
					String[] x = new String[100];
					for (String aux : linha.split(",")) {
						x[j] = aux;
						j++;
					}
					if (x[5].contains("Brazil")) {
						pilha.push(linha);
					}
					linha = buffer.readLine();
				}
				int tamanho = pilha.size();
				buffer.close();
				leitor.close();
				fluxo.close();
			}

		}
		return pilha;
	}

	@Override
	public void desempilhaBonsBrasileiros(Stack<String> pilha) throws IOException {
		int tamanho = pilha.size();
		String[] x = new String[100];
		System.out.println("------Lista de jogadores brasileiros com overall 80+");
		for (int i = 0; i < tamanho; i++) {
			String jogador = pilha.pop();
			int j = 0;
			for (String aux : jogador.split(",")) {
				x[j] = aux;
				j++;
			}
			if ((Integer.parseInt(x[7])) > 80) {
				System.out.println("Jogador: " + x[2] + " Overall: " + x[7]);
			}
		}

	}

	@Override
	public List<String> listaRevelacoes(String caminho, String nome) throws IOException {
		List<String> lista = new LinkedList<String>();
		File dir = new File(caminho);
		File arq = new File(caminho, nome);
		if (dir.exists() && dir.isDirectory()) {
			if (arq.exists()) {
				FileInputStream fluxo = new FileInputStream(arq);
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				linha = buffer.readLine();

				while (linha != null) {
					int j = 0;
					String[] x = new String[100];
					for (String aux : linha.split(",")) {
						x[j] = aux;
						j++;
					}
					if ((Integer.parseInt(x[3])) <= 20) {
						lista.add(linha);
					}
					linha = buffer.readLine();
				}
				int tamanho = lista.size();
				buffer.close();
				leitor.close();
				fluxo.close();
			}

		}
		return lista;
	}

	@Override
	public void buscaListaBonsJovens(List<String> lista) throws IOException {
		int tamanho = lista.size();
		String[] x = new String[100];
		Iterator<String> it = lista.iterator();
		System.out.println("\n\n---------- Lista de Jogadores com 20- anos e Overall 80+");
		while (it.hasNext()) {
			String jogador = it.next();
			int j = 0;
			for (String aux : jogador.split(",")) {
				x[j] = aux;
				j++;
			}
			if ((Integer.parseInt(x[7])) > 80) {
				System.out.println("Jogador: " + x[2] + " Idade: " + x[3] + " Overall: " + x[7]);
			}
		}
	}
}