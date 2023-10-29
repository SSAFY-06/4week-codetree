/*
문제
0부터 9까지의 임의의 숫자가 그려진 n * m 격자판, 한 면이 1 * 1 크기인 정육면체 굴리기
처음 정육면체의 각 면은 0으로 채워짐
바닥에 있는 칸의 숫자에 따라 해당 칸과 정육면체의 숫자가 변하게 됨
칸에 쓰여져 있는 수가 0이면, 주사위의 바닥면에 쓰여져있는 수가 칸에 복사됨, 이때 정육면체의 숫자는 변하지 않음
칸에 쓰여져 있는 수가 0이 아니면 칸에 쓰여져있는 수가 정육면체 바닥면으로 복사되며, 해당 칸의 수는 0이 됨
정육면체는 격자판 밖으로 이동할 수 없음, 만약 바깥으로 이동하려면 해당 시도를 무시하며 출력 하지 않음
정육면체의 초기 위치와 굴리는 방향이 주어질 때, 이동할 때마다 정육면체의 상단 면에 쓰여져 있는 숫자를 출력

입력
첫째 줄: 말판의 세로 크기 n, 가로 크기 m, 정육면체의 처음 위치 x, y, 그리고 굴리기 횟수 k
정육면체의 처음 위치 x는 위부터 아래까지 0부터 n-1까지 차례대로 번호를 매겨 구분, y는 왼쪽에서 오른쪽까지 차례대로 번호를 매겨 구분
n개 줄: 지도에 쓰여져있는 수
마지막 줄: 굴리기 방향(1이상 4이하의 숫자 중 하나, 순서대로 동쪽, 서쪽, 북쪽, 남쪽, 말판의 범위를 벗어나는 방향이 주어졌을 경우 굴리기 수행 X)

제한사항
1 ≤ n, m ≤ 20
0 ≤ x ≤ n - 1, 0 ≤ y ≤ m-1
1 ≤ k ≤ 1000
0 ≤ 말판에 쓰여진 수 ≤ 9
주사위가 초기에 위치하는 칸은 초기값이 0이라고 가정 가능

출력
각각 굴리기 시행 이후에 정육면체 상단 면에 있는 숫자를 출력

아이디어
배치: 상단-0,북-1,동-2,남-3,서-4,하단-5

방향에 따른 새로운 배치
1 0-2 1-1 2-5 3-3 4-0 5-4
2 0-4 1-1 2-0 3-3 4-5 5-2
3 0-1 1-5 2-2 3-0 4-4 5-3
4 0-3 1-0 2-2 3-5 4-4 5-1

*/
import java.io.*;
import java.util.*;
public class Main {

    static int N, M, X, Y, K;
    static int[][] arr;
    static int[] roll;
    static int[] dice;

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        roll = new int[K];
        dice = new int[6];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            roll[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<K; i++) {
            execute(roll[i]);
        }

    }

    static void execute(int dir) {
        if((X==N-1 && dir==4) || (X==0 && dir==3) || (Y==0 && dir==2) || (Y==M-1 && dir==1)) return;
        int temp = dice[0];
        switch(dir) {
            case 1:
                // 동쪽 이동
                Y++;
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[2];
                dice[2] = temp;
                break;
            case 2:
                // 서쪽 이동
                Y--;
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;
                break;
            case 3: 
                // 북쪽 이동
                X--;
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
                break;
            case 4: 
                // 남쪽 이동
                X++;
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
                break;
        }
        // 복사 및 판 수정
        if(arr[X][Y]==0) {
            arr[X][Y] = dice[5];
        }
        else {
            dice[5] = arr[X][Y];
            arr[X][Y] = 0;
        }
        System.out.println(dice[0]);
    }
}