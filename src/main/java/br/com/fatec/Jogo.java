package br.com.fatec;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Jogo {

    private static String montarTextoClassificacao(String titulo, List<Piloto> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append(titulo).append("\n");
        for (int i = 0; i < lista.size(); i++) {
            Piloto p = lista.get(i);
            sb.append((i + 1)).append(". ").append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Random gerador = new Random();
        PartidaDAO dao = new PartidaDAO();
        int opcao = -1;

        while (opcao != 0) {
            String entrada = JOptionPane.showInputDialog(
                "1 - Novo campeonato\n2 - Ranking\n0 - Sair"
            );

            if (entrada == null) {
                break;
            }

            opcao = Integer.parseInt(entrada);

            if (opcao == 1) {
                Concessionaria concessionaria = new Concessionaria();
                Campeonato campeonato = new Campeonato();

                String escolhaPiloto = JOptionPane.showInputDialog(
                    "Escolha seu piloto:\n1 - Jason\n2 - Lucia"
                );

                Piloto jason = new Piloto("Jason", concessionaria.sortear());
                Piloto lucia = new Piloto("Lucia", concessionaria.sortear());
                Piloto raul = new Piloto("Raul", concessionaria.sortear());
                Piloto cal = new Piloto("Cal", concessionaria.sortear());

                Piloto jogador = escolhaPiloto.equals("1") ? jason : lucia;

                campeonato.inscrever(jason);
                campeonato.inscrever(lucia);
                campeonato.inscrever(raul);
                campeonato.inscrever(cal);

                int cargasNitro = 2;

                for (int corrida = 1; corrida <= 3; corrida++) {
                    boolean usarNitro = false;

                    if (cargasNitro > 0) {
                        int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Corrida " + corrida + " de 3\nDeseja usar nitro? (" + cargasNitro + " carga(s) restante(s))",
                            "Nitro",
                            JOptionPane.YES_NO_OPTION
                        );
                        if (resposta == JOptionPane.YES_OPTION) {
                            usarNitro = true;
                            cargasNitro--;
                        }
                    }

                    List<Piloto> chegada = campeonato.disputarCorrida(gerador, jogador, usarNitro);
                    String textoChegada = montarTextoClassificacao("Chegada da Corrida " + corrida + ":", chegada);
                    JOptionPane.showMessageDialog(null, textoChegada);
                }

                List<Piloto> classificacaoFinal = campeonato.getClassificacao();
                String textoFinal = montarTextoClassificacao("Classificacao Final:", classificacaoFinal);
                JOptionPane.showMessageDialog(null, textoFinal);

                Partida partida = Partida.builder()
                    .jogador(jogador.getNome())
                    .pontuacao(jogador.getPontos())
                    .build();
                dao.cadastrar(partida);
                JOptionPane.showMessageDialog(null, "Partida salva! Pontuacao de " + jogador.getNome() + ": " + jogador.getPontos());

            } else if (opcao == 2) {
                List<Partida> partidas = dao.listar();

                Collections.sort(partidas, (p1, p2) -> {
                    if (p1.getPontuacao() != p2.getPontuacao()) {
                        return Integer.compare(p2.getPontuacao(), p1.getPontuacao());
                    }
                    return Integer.compare(p2.getCodigo(), p1.getCodigo());
                });

                StringBuilder sb = new StringBuilder();
                sb.append("Ranking (Top 5):\n");

                int limite = partidas.size() < 5 ? partidas.size() : 5;
                for (int i = 0; i < limite; i++) {
                    Partida p = partidas.get(i);
                    sb.append((i + 1)).append(". ")
                      .append(p.getJogador()).append(" - ")
                      .append(p.getPontuacao()).append(" pontos\n");
                }

                JOptionPane.showMessageDialog(null, sb.toString());
            }
        }
    }
}