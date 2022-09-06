import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        
        int i = 2; //defina aqui qual questao quer rodar

        if(i == 1){
            //comeca no estado 2 e o objetivo esta em um estado aleatorio
            Q1_Learning Q1 = new Q1_Learning(2,random.nextInt(36));
            Q1.start();
        }
        if(i == 2){
            //comeca em um estado aleatorio(logica dentro da classe Q2_larning) e 
            //o objetivo esta no estado 29saida)
            /*
            * obs: pela quantidade de estados, a matriz Q ficou muito grande,
            * além disso, também é gerado um arquivo txt ao final, para melhor visualização.
            * a questao 2 so e possivel verificar toda a tabela Q pelo arquivo txt.
            *
            * obs: pode acontecer da tabela vir toda zerada. pois ele nao encontrou dentro
            * do numero de movimentos o estado de recompensa, entao recomendo rodar mais de uma vez
            *
            * no inicio da execucao ele abre o arquivo txt e prrenche a matriz com ele, mantendo o aprendizado.
            * caso queira zerar a matriz, basta excluir o arquivo q2_tabelaQ.
            */
            Q2_Learning Q2 = new Q2_Learning(29);
            Q2.start();
        }
    }
}
