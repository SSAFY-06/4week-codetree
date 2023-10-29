/**
 * 방화벽 추가 3개 설치
 * 불이 퍼지지 않는 영역이 최대일 때의 크기 출력
 *
 * 입력
 * n,m
 * 불이 있는 경우 2 방화벽 1 빈칸 0
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 방화벽을 dfs을 이용하여 세우고
 * 세운 시점에서 bfs를 이용하여 불을 퍼뜨린다.
 */

public class Main {
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,-1,0,1};

    static int[][] map;
    static class Point{
        int r;
        int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static boolean[][] visited;
    static List<Point> fires;
    static int N,M;
    static int wallCnt = 0;
    static int answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        fires = new ArrayList<>();
        answer = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]= Integer.parseInt(st.nextToken());
                //불이면
                if(map[i][j]==2) fires.add(new Point(i,j));
                if(map[i][j]==1) wallCnt++;
            }
        }

        //벽 3개를 더 세워야하기 때문에
        wallCnt+=3;

        makeWall(0 ,0);
        System.out.println(answer);
    }

    /**
     *
     * @param depth : 벽의 개수
     * @param idx : 시작 인덱스
     */
    private static void makeWall(int depth, int idx) {
        //기저 조건
        if(depth==3){
            getArea();
            return;
        }

        for(int i=idx;i<N*M;i++){
            int r = i/M;    //행 처리
            int c = i%M;    //열
            //빈칸일때만 벽을 세울 수 있으므로
            if(map[r][c]!=0) continue;
            //해당 위치에 벽 세워주고
            map[r][c] = 1;
            makeWall(depth+1, idx+1);
            //안된 경우 다시 빈칸으로 바꿔주기
            map[r][c] = 0;
        }

    }

    private static void getArea() {
        Queue<Point> q = new LinkedList<>(fires);
        visited = new boolean[N][M];

        checkfire();

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc]==0){
                    visited[nr][nc]=true;
                    q.offer(new Point(nr, nc));
                }
            }
        }

        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visited[i][j]) cnt++;
            }
        }

        answer = Math.max(cnt-wallCnt, answer);

    }

    //불 표시
    private static void checkfire(){
        for(int i=0;i<fires.size();i++){
            visited[fires.get(i).r][fires.get(i).c]=true;
        }
    }

    private static boolean isInRange(int x, int y){
        return x >=0 && y>=0 && x<N && y<M;
    }


}
