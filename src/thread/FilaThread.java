package src.thread;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FilaThread extends Thread{

    private static ConcurrentLinkedQueue<ObjetoThread> pilha_fila = new ConcurrentLinkedQueue<ObjetoThread>();

    public static void add(ObjetoThread objetoThread){

        pilha_fila.add(objetoThread);
    }

    @Override
    public void run() {

        System.out.println("Fila rodando");

        while (true) {
            synchronized (pilha_fila) {//bloquear o acesso a essa lista por outros processos (apenas essa thread acessa essa lista de processamento)

                Iterator iterator = pilha_fila.iterator();
                while (iterator.hasNext()) { //enquanto conter dados na lista irá processar

                    ObjetoThread processar = (ObjetoThread) iterator.next(); //Pega o objeto atual e processa

                    System.out.println("-----------------------");
                    System.out.println(processar.getNome());
                    System.out.println(processar.getEmail());

                    iterator.remove(); //após processado remove o objeto

                    try {
                        Thread.sleep(900); //dar um tempo para descarga de memória
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try {
                Thread.sleep(1000); //processou toda a lista tempo para descarga de memória
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
