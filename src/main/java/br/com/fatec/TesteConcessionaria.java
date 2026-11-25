package br.com.fatec;

public class TesteConcessionaria {
    public static void main(String[] args) {
        Concessionaria c = new Concessionaria();
        System.out.println("Carros no estoque: " + c.restantes());

        for (int i = 0; i < 3; i++) {
            Carro sorteado = c.sortear();
            System.out.println("Sorteou: " + sorteado);
        }

        System.out.println("Carros no estoque: " + c.restantes());
    }
}