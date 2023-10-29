import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] customer;
    static int[] examine;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        customer = new int[N];
        examine = new int[2];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            customer[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2;i++){
            examine[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        for(int i=0;i<N;i++){
            //팀장
            answer += 1;
            customer[i]-=examine[0];

            if(customer[i]<=0) continue;

            if(customer[i]%examine[1]==0){
                answer += customer[i]/examine[1];
            }
            else{
                answer += customer[i]/examine[1]+1;
            }
        }

        System.out.println(answer);
    }
}
