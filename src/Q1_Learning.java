import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/*
 * autor: Lauro Santana Silva
 */

public class Q1_Learning {

    private int campo[][] = {
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0}
    };
    private int S[] = new int[(campo.length*campo.length)];

    private int A[] = new int[4];

    private double Q[][] = new double[(campo.length*campo.length)][4];

    private int goal;
    private int start;
    private int num_movimento;
    private int estado_atual;
    private int estado_anterior;
    private boolean aleatorio = true;
    private Random random = new Random();
    private double fator_apd = 0.9;
    
    /**
     * 
     * @param start estado de inicio
     * @param goal estado do objetivo
     * @param num_movimento estado do objetivo
     */
    public Q1_Learning(int start, int goal, int num_movimento){
        this.start = start;
        this.num_movimento = num_movimento;
        this.estado_atual = start;
        this.goal = goal;

        for(int i = 0; i < S.length; i++){
            if(i == goal)
                this.S[i] = 10;
            else
                this.S[i] = 0;
        }
        
        try {
            FileReader q1_tableQ = new FileReader(System.getProperty("user.dir") + "\\q1_tabelaQ.txt");
            Scanner scan = new Scanner(q1_tableQ);
            String linha = "";
            while(scan.hasNextLine()){
                for(int i = 0; i < Q.length; i++){
                    linha = scan.nextLine();
                    for(int c = 0; c < 4; c++){
                        this.Q[i][c] = Double.parseDouble(linha.split(";")[c]);
                    }
                }
            }
            //quando existir o arquivo ele observa o peso das açaoes
            //mas quando todos os pesos sao 0, ele continua aleatorio.
            this.aleatorio = false;
            scan.close();
        } catch (FileNotFoundException e) {
            //se o arquivo nao existir ele preenche a matriz com 0
            for(int i = 0; i < Q.length; i++){
                for(int c = 0; c < 4; c++){
                    this.Q[i][c] = 0;
                }
            }
        }
        
        A[0] = 0; //subir
        A[1] = 1; //descer
        A[2] = 2; //ir pra esquerda
        A[3] = 3; //ir pra direita
    }

    public void start() throws IOException{
        int movimentos = 0;
        int acao;
        int v_aux[] = new int[2];
        while(movimentos < num_movimento){
            if(aleatorio)
                acao = random.nextInt(4);
            else
                acao = move(estado_atual);
            v_aux = verificaMove(A[acao], estado_atual);
            if(v_aux != null){
                //subida
                if(A[acao] == 0){
                    v_aux[0] += -1;
                    //System.out.println("Indo para " + v_aux[0] + ":" + v_aux[1] + " subindo");
                    estado_anterior = estado_atual;
                    estado_atual = 0;
                    for(int i = 0; i < campo.length; i++){
                        for(int c = 0; c < campo.length ; c++){
                            if(c == v_aux[1] && i == v_aux[0]){
                                i = campo.length;
                                break;
                            }
                            estado_atual++;
                        }
                    }
                    Q[estado_anterior][A[acao]] = S[estado_atual] + (fator_apd * max(estado_atual));

                }
                //descida
                else if(A[acao] == 1){
                    v_aux[0] += 1;
                    //System.out.println("Indo para " + v_aux[0] + ":" + v_aux[1] + " descendo");
                    estado_anterior = estado_atual;
                    estado_atual = 0;
                    for(int i = 0; i < campo.length; i++){
                        for(int c = 0; c < campo.length ; c++){
                            if(c == v_aux[1] && i == v_aux[0]){
                                i = campo.length;
                                break;
                            }
                            estado_atual++;
                        }
                    }
                    Q[estado_anterior][A[acao]] = S[estado_atual] + (fator_apd * max(estado_atual));
                }
                //para a esquerda
                else if(A[acao] == 2){
                    v_aux[1] += -1;
                    //System.out.println("Indo para " + v_aux[0] + ":" + v_aux[1] + " pela esquerda");
                    estado_anterior = estado_atual;
                    estado_atual = 0;
                    for(int i = 0; i < campo.length; i++){
                        for(int c = 0; c < campo.length ; c++){
                            if(c == v_aux[1] && i == v_aux[0]){
                                i = campo.length;
                                break;
                            }
                            estado_atual++;
                        }
                    }
                    Q[estado_anterior][A[acao]] = S[estado_atual] + (fator_apd * max(estado_atual));
                }
                //para a direita
                else if(A[acao] == 3){
                    v_aux[1] += 1;
                    //System.out.println("Indo para " + v_aux[0] + ":" + v_aux[1] + " pela direita");
                    estado_anterior = estado_atual;
                    estado_atual = 0;
                    for(int i = 0; i < campo.length; i++){
                        for(int c = 0; c < campo.length ; c++){
                            if(c == v_aux[1] && i == v_aux[0]){
                                i = campo.length;
                                break;
                            }
                            estado_atual++;
                        }
                    }
                    Q[estado_anterior][A[acao]] = S[estado_atual] + (fator_apd * max(estado_atual));
                }
                movimentos++;
            }
        }
        FileWriter c_arquivo = new FileWriter(System.getProperty("user.dir") + "\\q1_tabelaQ.txt", false);
        BufferedWriter buffer = new BufferedWriter(c_arquivo);
        PrintWriter escritor = new PrintWriter(buffer);
        
        System.out.println("Matriz Q");
        for(int i = 0; i < Q.length; i++){
            for(int c = 0; c < 4; c++){
                System.out.print(Q[i][c] + " ");
                escritor.append(Q[i][c] + ";");
            }
            System.out.println();
            escritor.append("\n");
        }
        escritor.close();
        buffer.close();
        c_arquivo.close();
    }

    private int[] verificaMove(int move, int estado){
        int aux = 0;
        int v_aux[] = new int[2];
        for(int i = 0; i < campo.length; i++){
            for(int c = 0; c < campo.length; c++){
                if(aux == estado){
                    //subida
                    if(move == 0){
                        if(i != 0){
                            v_aux[0] = i;
                            v_aux[1] = c;
                            return v_aux;
                        }
                    }
                    //descida
                    if(move == 1){
                        if(i+1 != campo.length){
                            v_aux[0] = i;
                            v_aux[1] = c;
                            return v_aux;
                        }
                    }
                    //para a esquerda
                    if(move == 2){
                        if(c != 0){
                            v_aux[0] = i;
                            v_aux[1] = c;
                            return v_aux;
                        }
                    }
                    //para a direita
                    if(move == 3){
                        if(c+1 != campo.length){
                            v_aux[0] = i;
                            v_aux[1] = c;
                            return v_aux;
                        }
                    }
                    return null;
                }
                aux++;
            }
        }
        return null;
    }

    private double max(int estado){
        double maior = 0;
        for(int i = 0; i < 4; i++){
            if(Q[estado][i] > maior){
                maior = Q[estado][i];
            }
        }
        return maior;
    }

    private int move(int estado){
        int acao = random.nextInt(4);
        double maior = 0;
        for(int i = 0; i < 4; i++){
            if(Q[estado][i] > maior){
                acao = i;
            }
        }
        return acao;
    }
}
