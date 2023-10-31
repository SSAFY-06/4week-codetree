import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 격자판 0~9까지의 숫자가 적혀있음
 * 정육면체는 모두 0으로 되어있다
 *
 * 바닥의 칸의 숫자에 따라 해당 칸의 정육면체의 숫자가 바뀜
 *
 * 규칙
 * 1. 칸에 쓰여 있는 숫자 0이면 주사위의 바닥면에 쓰여져있는 수가 칸에 복사
 * 2. 칸에 쓰여 있는 수자가 0이 아니면 칸에 쓰여있는 수가 정육면체 바닥면으로 복사 -> 해당 칸은 0이 됨
 *
 * 문제 접근 방법
 * 정육면체를 배열로 나타낸다고 했을 떄
 * 정육면체가 마주보는 면의 합을 5라고 하자
 * 그렇다면 바닥면을 칸에 복사하든 아님 바닥면의 값이 바뀌든 해당 바닥면의 idx만큼을 5에서 뺀 값이 위를 바라보는 정육면체의 idx가 될것
 *
 */

public class 정육면체굴리기 {
    static final int EAST=1, WEST=2, NORTH=3, SOUTH=4;  //예는 쓸지 안쓸지 모르겠음

    static final int[] dr = {0,0,0,-1,1};
    static final int[] dc = {0,1,-1,0,0};
    static int N, M, X, Y, K;   //N : 격자판의 세로, M : 격자판의 가로, X, Y : 정육면체의 위치, K : 굴리기 횟수
    static int[] arr;
    static int[] dice;
    static int[][] map;
    static int[] move;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        move = new int[K];
        dice = new int[6];
        arr = new int[6];   //임시 데이터 저장용

        map = new int[N][M];

        //map 입력받기
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            move[i]=Integer.parseInt(st.nextToken());
        }
//        System.out.println("map");
//        print();

//        System.out.println("move");
//        System.out.println(Arrays.toString(move));
        //주사위를 굴리는 횟수만큼 반복
        /**
         * 시작 면
         * 앞 : 2
         * 뒤 : 3
         * 아래 : 0
         * 위 : 5
         * 왼 : 1
         * 오 : 4
         */
        /**
         * 아래로 굴렸을 때
         * 앞 : 5
         * 뒤 : 0
         * 아래 : 2
         * 위 : 3
         * 왼 : 1
         * 오 : 4
         */
        /**
         * 위로 굴렸을 때
         * 앞 : 0
         * 뒤 : 5
         * 아래 : 3
         * 위 : 2
         * 왼 : 1
         * 오 : 4
         */
        /**
         * 왼쪽으로 굴렸을 때
         * 앞 : 2
         * 뒤 : 3
         * 아래 : 1
         * 위 : 4
         * 왼 : 5
         * 오 : 0
         */
        /**
         * 오른쪽으로 굴렸을 때
         * 앞 : 2
         * 뒤 : 3
         * 아래 : 4
         * 위 : 1
         * 왼 : 0
         * 오 : 5
         */
        for(int i=0;i<K;i++){
            int nr = X+dr[move[i]];
            int nc = Y+dc[move[i]];

//            System.out.println("i: "+i);
//            System.out.println("nr: "+nr + ", nc: "+nc);

            System.arraycopy(dice, 0, arr, 0, 6);

//            System.out.println("arr");
//            System.out.println(Arrays.toString(arr));

            if(!isInRange(nr, nc)) continue;

            int nv = map[nr][nc];

//            System.out.println("nv: "+nv);

            if(move[i]==SOUTH){
                dice[0]=arr[2];
                dice[5]=arr[3];
                dice[2]=arr[5];
                dice[3]=arr[0];
            }
            else if(move[i]==NORTH){
                dice[2]=arr[0];
                dice[3]=arr[5];
                dice[0]=arr[3];
                dice[5]=arr[2];
            }
            else if(move[i]==WEST){
                dice[0]=arr[1];
                dice[5]=arr[4];
                dice[1]=arr[5];
                dice[4]=arr[0];
            }
            else if(move[i]==EAST){
                dice[0]=arr[4];
                dice[5]=arr[1];
                dice[1]=arr[0];
                dice[4]=arr[5];
            }

//            System.out.println("dice");
//            System.out.println(Arrays.toString(dice));

            if(nv==0){
                map[nr][nc]=dice[0];
            }
            else{
                dice[0]=map[nr][nc];
                map[nr][nc]=0;
            }

//            System.out.println("after dice");
//            System.out.println(Arrays.toString(dice));

            System.out.println(dice[5]);

            X=nr;
            Y=nc;
        }

    }

    static boolean isInRange(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

    static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
