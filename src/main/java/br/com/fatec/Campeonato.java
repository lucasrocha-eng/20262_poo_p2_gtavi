package br.com.fatec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Campeonato {
    private static final int[] PONTOS = {10, 6, 3, 1};
    private List<Piloto> pilotos;

    public Campeonato() {
        this.pilotos = new ArrayList<>();
    }

    public void inscrever(Piloto piloto) {
        if (pilotos.size() >= 4) {
            System.out.println("Grid cheio.");
        } else if (pilotos.contains(piloto)) {
            System.out.println("Piloto repetido.");
        } else {
            pilotos.add(piloto);
        }
    }

    public void registrarCorrida(List<Piloto> chegada) {
        for (int i = 0; i < chegada.size(); i++) {
            Piloto p = chegada.get(i);
            p.somarPontos(PONTOS[i]);
            if (i == 0) {
                p.registrarVitoria();
            }
        }
    }

    public List<Piloto> disputarCorrida(Random gerador, Piloto jogador, boolean nitro) {
        for (Piloto p : pilotos) {
            boolean usaNitro = nitro && p.equals(jogador);
            p.correr(gerador, usaNitro);
        }

        List<Piloto> chegada = new ArrayList<>(pilotos);
        Collections.sort(chegada, (p1, p2) -> Integer.compare(p1.getTempo(), p2.getTempo()));

        registrarCorrida(chegada);
        return chegada;
    }

    public List<Piloto> getClassificacao() {
        List<Piloto> classificacao = new ArrayList<>(pilotos);
        Collections.sort(classificacao, (p1, p2) -> {
            if (p1.getPontos() != p2.getPontos()) {
                return Integer.compare(p2.getPontos(), p1.getPontos());
            }
            if (p1.getVitorias() != p2.getVitorias()) {
                return Integer.compare(p2.getVitorias(), p1.getVitorias());
            }
            return p1.getNome().compareTo(p2.getNome());
        });
        return classificacao;
    }
}