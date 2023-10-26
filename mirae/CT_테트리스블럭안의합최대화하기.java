package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* 문제]
* n x m 격자판
*
* 풀이]
*/
public class CT_테트리스블럭안의합최대화하기 {
	static int N, M, map[][], SUM;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	static boolean visited[][];
	static class Point {
		int r, c, s;
		public Point(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());        	
            for (int j = 0; j < M; j++) {
            	map[i][j] = Integer.parseInt(st.nextToken());				
			}
        }
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		dfs(1, i, j, map[i][j]);
        		mountainShape(i, j);
        	}
        }
        System.out.println(SUM);
	}

	private static void mountainShape(int i, int j) {
		int sum = map[i][j];
		for(int d=0; d<4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(isOutOfRange(nr, nc)) continue;
			sum += map[nr][nc];
		}
		for(int d=0; d<4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			int r = sum;
			if(!isOutOfRange(nr, nc)) r-=map[nr][nc];
			SUM = Math.max(SUM, r);
		}
	}

	private static void dfs(int cnt, int r, int c, int sum) {
		if(cnt == 4) {
			SUM = Math.max(SUM, sum);
			return;
		}
		visited[r][c] = true;
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(isOutOfRange(nr, nc) || visited[nr][nc]) continue;
			dfs(cnt+1, nr, nc, sum+map[nr][nc]);
		}
		visited[r][c] = false;		
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}

}
