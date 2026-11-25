package br.com.fatec;

import lombok.*;

@Getter
@AllArgsConstructor

public class Carro implements Comparable<Carro> {
    private String modelo;
    private int potencia;

    public int getTempoDeVolta() {
        return 30 + 6000 / potencia;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%d cv)", modelo, potencia);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Carro)) {
            return false;
        }
        Carro outro = (Carro) obj;
        return this.modelo.equals(outro.modelo);
    }
    
    @Override
    public int compareTo(Carro outro) {
        if (this.potencia > outro.potencia) {
            return -1;
        }
        if (this.potencia < outro.potencia) {
            return 1;
        }
        return this.modelo.compareTo(outro.modelo);
    }
}


