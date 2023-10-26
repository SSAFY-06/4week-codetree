package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* 문제]
* n x m 격자판
* 방화벽 3개 설치
* 상하좌우로 불 번짐
* 불이 퍼지지 않는 영역의 최대 크기 = 전체 크기 - 불이 퍼진 크기
* 
*
* 풀이]
* 빈칸 3곳 선택
* 선택한 3곳에 대해 불의 위치에서 bfs 시작
* 
* 
*/
public class CT_방화벽설치하기 {
	static final int FIRE=2, WALL=1, EMPTY=0;
	static int N, M, map[][], SPACE, WALLS;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	static List<Point> fires;
	static boolean visited[][];
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        fires = new ArrayList<Point>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());        	
            for (int j = 0; j < M; j++) {
            	map[i][j] = Integer.parseInt(st.nextToken());
            	if(map[i][j] == FIRE) fires.add(new Point(i, j));
            	if(map[i][j] == WALL) WALLS++;
			}
        }
        WALLS += 3;
        combi(0, 0);
        System.out.println(SPACE);
	}

	private static void combi(int cnt, int str) {
		if(cnt==3) {
			updateMaxArea();
			return;
		}
		
		for(int i=str; i<N*M; i++) {
			int r = i/M;
			int c = i%M;
			if(map[r][c] != EMPTY) continue;
			map[r][c] = WALL;
			combi(cnt+1, i+1);
			map[r][c] = EMPTY;
		}
	}

	private static void updateMaxArea() {
		int fireCnt = 0;
		resetVisited();
		Queue<Point> queue = new ArrayDeque<Point>();
		for(Point p : fires) {
			queue.add(p);
			visited[p.r][p.c] = true;
		}
		
		
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			fireCnt++;
			for(int d=0; d<4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if(isOutOfRange(nr, nc) || visited[nr][nc] || map[nr][nc] != EMPTY) continue;
				visited[nr][nc] = true;
				queue.add(new Point(nr, nc));
			}
		}
//		System.out.println(N*M - fireCnt - WALLS);
//		print();
		SPACE = Math.max(SPACE, N*M - fireCnt - WALLS);
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}

	private static void resetVisited() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}

}
