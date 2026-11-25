package br.com.fatec;

import java.util.Random;
import lombok.Getter;

@Getter
public class Piloto {
    private String nome;
    private Carro carro;
    private int pontos;
    private int vitorias;
    private int tempo;

    public Piloto(String nome, Carro carro) {
        this.nome = nome;
        this.carro = carro;
        this.pontos = 0;
        this.vitorias = 0;
    }

    public void correr(Random gerador, boolean nitro) {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            int atraso = gerador.nextInt(5);
            total += carro.getTempoDeVolta() + atraso;
        }
        if (nitro) {
            total -= 10;
        }
        this.tempo = total;
    }

    public void somarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void registrarVitoria() {
        this.vitorias++;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Piloto)) {
            return false;
        }
        Piloto outro = (Piloto) obj;
        return this.nome.equals(outro.nome);
    }

    @Override
    public String toString() {
        return nome + " " + pontos + " pontos " + vitorias + " vitoria(s)";
    }
}