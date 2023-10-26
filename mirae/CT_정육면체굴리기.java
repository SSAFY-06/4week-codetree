package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
* 문제]
* n x m 격자판
* 정육면체
* 칸에 쓰여있는 수가 0 - 주사위의 바닥면의 수가 칸에 복사
* 칸에 쓰여있는 수가 0이 X - 칸에 쓰인 수가 정육면체 바닥면에 복사, 해당 칸 0
* 칸 밖으로 이동 X, 해당 이동은 무시
* 움직임 마다 상단에 쓰인 숫자 출력
*
* 풀이]
* 주사위 모양
* 0 1 0
* 0 1 0
* 1 2 1
* 0 1 0
* - 2가 바닥면
* - 1은 나머지 숫자
*/
public class CT_정육면체굴리기 {
	static final int RIGHT=1, LEFT=2, UP=3, DOWN=4;
	static int N, M;
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());        	
            for (int j = 0; j < M; j++) {
            	map[i][j] = Integer.parseInt(st.nextToken());				
			}
        }
        st = new StringTokenizer(br.readLine());
        
        int[][] dice = new int[4][3];
        StringBuilder sb = new StringBuilder();
        if(map[x][y] == 0) map[x][y] = 0;
        else {
        	dice[2][1] = map[x][y];
        	map[x][y] = 0;
        }
        int d, temp;
        for (int i = 0; i < K; i++) {
        	d = Integer.parseInt(st.nextToken());
        	int nr = x + dr[d];
        	int nc = y + dc[d];
        	if(isOutOfRange(nr, nc)) continue;
        	switch(d) {
        	case RIGHT:
        		temp = dice[2][0];
        		dice[2][0] = dice[2][1];
        		dice[2][1] = dice[2][2];
        		dice[2][2] = dice[0][1];
        		dice[0][1] = temp;
        		break;
        	case LEFT:
        		temp = dice[2][2];
        		dice[2][2] = dice[2][1];
        		dice[2][1] = dice[2][0];
        		dice[2][0] = dice[0][1];
        		dice[0][1] = temp;
        		break;
        	case UP:
        		temp = dice[3][1];
        		for(int r=3; r>0; r--) dice[r][1] = dice[r-1][1];
        		dice[0][1] = temp;
        		break;
        	case DOWN:
        		temp = dice[0][1];
        		for(int r=0; r<3; r++) dice[r][1] = dice[r+1][1];
        		dice[3][1] = temp;
        	}
        	x = nr;
        	y = nc;
        	if(map[x][y] == 0) map[x][y] = dice[2][1];
            else {
            	dice[2][1] = map[x][y];
            	map[x][y] = 0;
            }
		}
        System.out.println(sb);
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}

}
