package br.com.fatec;

import java.util.List;

public class TestePartidaDAO {
    public static void main(String[] args) {
        PartidaDAO dao = new PartidaDAO();

        Partida p1 = Partida.builder()
            .jogador("Ana")
            .pontuacao(26)
            .build();

        Partida p2 = Partida.builder()
            .jogador("Bia")
            .pontuacao(13)
            .build();

        System.out.println("Cadastrou Ana? " + dao.cadastrar(p1));
        System.out.println("Cadastrou Bia? " + dao.cadastrar(p2));

        List<Partida> partidas = dao.listar();
        for (Partida p : partidas) {
            System.out.println(p.getCodigo() + " - " + p.getJogador() + " - " + p.getPontuacao());
        }
    }
}