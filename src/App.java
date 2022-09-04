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
            //comeca em um estado aleatorio e o objetivo esta no estado 22(saida)
            /*
            * obs: pela quantidade de estados, a matriz Q ficou muito grande,
            * além disso, também é gerado um arquivo txt ao final, para melhor visualização.
            * a questao 2 so e possivel verificar toda a tabela Q pelo arquivo txt.
            */
            Q2_Learning Q2 = new Q2_Learning(random.nextInt(1739), 22);
            Q2.start();
        }
    }
}
