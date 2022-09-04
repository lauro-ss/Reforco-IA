import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        
        //comeca no estado 2 e o objetivo esta em um estado aleatorio
        Q1_Learning Q1 = new Q1_Learning(2,random.nextInt(36));
        Q1.start();

    }
}
