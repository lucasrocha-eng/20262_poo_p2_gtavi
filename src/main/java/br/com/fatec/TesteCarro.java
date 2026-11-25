package br.com.fatec;

import java.util.*; 

public class TesteCarro {
    public static void main(String[] args) {
        Carro c1 = new Carro("Grotti Cheetah", 500);
        Carro c2 = new Carro("Bravado Banshee", 400);
        Carro c3 = new Carro("Dinka Blista", 200);

        System.out.printf("%s faz a volta em %ds\n", c1, c1.getTempoDeVolta());
        System.out.printf("%s faz a volta em %ds\n", c2, c2.getTempoDeVolta());
        System.out.printf("%s faz a volta em %ds\n", c3, c3.getTempoDeVolta());

        System.out.println("Iguais? " + c1.equals(new Carro("Grotti Cheetah", 500)));
        System.out.println("Iguais? " + c1.equals(new Carro("Pegassi Infernus", 500)));

        List<Carro> carros = new ArrayList<>();
        carros.add(c3);
        carros.add(new Carro("Ocelot Pariah", 500));
        carros.add(c2);
        carros.add(c1);

        Collections.sort(carros);
        System.out.println(carros);
    }
}
                                        