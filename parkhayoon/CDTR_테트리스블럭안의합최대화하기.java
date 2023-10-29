/*
생각한 아이디어
1. nC4 후 연결되어 있는지 확인 - 많이 낭비될 듯?
- 풀어본 문제 중 백준 소문난칠공주 참고
https://www.acmicpc.net/source/67785552
조합 = 200*200C4 = 시간초과!
2. 한 점에서 연결된 점 1개 선택 -> 연결된 2점에서 연결된 1점 선택 -> ... -> 4개가 될 때까지

3. NOGADA
방향 목록
정사각형 - 1개(ㅁ)
일직선 - 2개(ㅡ,ㅣ)
L자 - 8개(L자 시계방향 회전 4방, 뒤집은 후 시계뱡향 회전 4방)
번개 - 4개(ㄴㄱ자 시계방향 회전 2방, 뒤집은 후 시계뱡향 회전 2방)
ㅏ 모양 - 4개(ㅏ자 시계방향 회전 4방)
*/
import java.io.*;
import java.util.*;
public class Main {

    static int N, M, maxSum = 0;
    static int[][] arr;
    static int[][][] deltas = { // int[19][4][2]

	// 정사각형
	{{0,0},{0,1},{1,0},{1,1}},

	// 일직선
	{{0,0},{0,1},{0,2},{0,3}},
	{{0,0},{1,0},{2,0},{3,0}},

	// 번개
	{{0,0},{1,0},{1,1},{2,1}},
	{{1,0},{1,1},{0,1},{0,2}},
	{{0,1},{1,1},{1,0},{2,0}},
	{{0,0},{0,1},{1,1},{1,2}},

	// ㅏ 모양
	{{0,0},{1,0},{2,0},{1,1}},
	{{0,1},{1,0},{1,1},{1,2}},
	{{1,0},{0,1},{1,1},{2,1}},
	{{0,0},{0,1},{0,2},{1,1}},
	
	// L자
	{{0,0},{1,0},{2,0},{2,1}},
	{{1,0},{0,0},{0,1},{0,2}},
	{{0,0},{0,1},{1,1},{2,1}},
	{{1,0},{1,1},{1,2},{0,2}},
	{{0,1},{1,1},{2,1},{2,0}},
	{{0,0},{1,0},{1,1},{1,2}},
	{{0,0},{1,0},{2,0},{0,1}},
	{{0,0},{0,1},{0,2},{1,2}}
	
};

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                for(int s = 0; s<19; s++) {
                    boolean isCreatable = true;
                    int sum = 0;
                    for(int d=0; d<4; d++) {
                        int dr = i+deltas[s][d][0];
                        int dc = j+deltas[s][d][1];
                        if(dr<0 || dr>=N || dc<0 || dc>=M) {
                            isCreatable = false;
                            break;
                        }
                        sum += arr[dr][dc];
                    }
                    if(isCreatable) {
                        maxSum = Math.max(maxSum, sum);
                    }
                }
            }
        }
        
        System.out.println(maxSum);

    }

}