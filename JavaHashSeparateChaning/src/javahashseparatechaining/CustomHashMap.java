package javahashseparatechaining;

import java.util.LinkedList;

public class CustomHashMap<T> {
    private LinkedList<Dado<T>>[] tabela;
    private int tamanho;
    private int numElementos;
    private int comparacoes;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private static class Dado<T> {
        long key;
        T value;

        Dado(long key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    public CustomHashMap(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int funcaoHash(long chave) {
        return (int) (Math.abs(chave) % tamanho);
    }

    public boolean put(long key, T value) {
        this.comparacoes = 0;
        int indice = funcaoHash(key);

        for (Dado<T> item : tabela[indice]) {
            this.comparacoes++;
            if (item.key == key) {
                item.value = value; // Apenas atualiza o valor
                return false; // Indica que a chave foi atualizada
            }
        }

        if ((double) numElementos / tamanho >= DEFAULT_LOAD_FACTOR) {
            resize();
            indice = funcaoHash(key); 
        }

        Dado<T> dado = new Dado<>(key, value);
        tabela[indice].add(dado);
        numElementos++;
        return true; // Indica que uma nova chave foi inserida
    }

    public boolean containsKey(long key) {
        this.comparacoes = 0;
        int indice = funcaoHash(key);

        for (Dado<T> item : tabela[indice]) {
            this.comparacoes++;
            if (item.key == key) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(long key) {
        this.comparacoes = 0;
        int indice = funcaoHash(key);
        Dado<T> paraRemover = null;

        for (Dado<T> item : tabela[indice]) {
            this.comparacoes++;
            if (item.key == key) {
                paraRemover = item;
                break;
            }
        }

        if (paraRemover != null) {
            tabela[indice].remove(paraRemover);
            numElementos--;
            return true;
        }

        return false;
    }

    public T get(long key) {
        this.comparacoes = 0;
        int indice = funcaoHash(key);

        for (Dado<T> item : tabela[indice]) {
            this.comparacoes++;
            if (item.key == key) {
                return item.value;
            }
        }
        return null;
    }

    public boolean replace(long key, T value) {
        this.comparacoes = 0;
        int indice = funcaoHash(key);

        for (Dado<T> item : tabela[indice]) {
            this.comparacoes++;
            if (item.key == key) {
                item.value = value;
                return true;
            }
        }
        return false;
    }

    public int getComparacoes() {
        return this.comparacoes;
    }

    private void resize() {
        System.out.println("Fator de carga excedido. Redimensionando tabela...");
        int novoTamanho = tamanho * 2;
        LinkedList<Dado<T>>[] tabelaAntiga = this.tabela;

        this.tamanho = novoTamanho;
        this.tabela = new LinkedList[novoTamanho];
        for (int i = 0; i < novoTamanho; i++) {
            this.tabela[i] = new LinkedList<>();
        }

        this.numElementos = 0;
        for (LinkedList<Dado<T>> lista : tabelaAntiga) {
            for (Dado<T> dado : lista) {
                int novoIndice = funcaoHash(dado.key);
                this.tabela[novoIndice].add(dado);
                this.numElementos++;
            }
        }
        System.out.println("Tabela redimensionada para capacidade " + novoTamanho + ".");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Tabela HASH ---\n");
        for (int i = 0; i < tamanho; i++) {
            sb.append("[").append(i).append("]");
            for (Dado<T> item : tabela[i]) {
                sb.append(" -> ").append(item.key).append(":").append(item.value);
            }
            sb.append("\n");
        }
        sb.append("-------------------\n");
        return sb.toString();
    }
}