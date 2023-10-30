import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dfs로 4개를 탐색하고
 * 해당 구역의 합을 구하기
 * bfs로 안한 이유: 테트리스 모양이 달라지는데 해당 모양대로 bfs를 하도록 설정해주는것이 너무 어려움
 */

public class Main {
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,-1,0,1};
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                dfs(1,i,j,map[i][j]);
                tmeeno(i,j);
            }
        }

        System.out.println(answer);

    }

    /**
     *
     * @param depth : 지금까지 선택한 블록의 개수
     * @param r : 현재 행, 다음 dfs 수행 시 다음 블록의 행을 넣을 거임
     * @param c : 현재 열
     * @param sum : 현재까지의 합
     */
    private static void dfs(int depth, int r, int c, int sum) {
        //기저 조건
        if(depth==4){
            answer = Math.max(answer, sum);
            return;
        }

        visited[r][c]=true;
        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(isInRange(nr, nc) && !visited[nr][nc]){
                dfs(depth+1, nr, nc, sum+map[nr][nc]);
            }
        }
        visited[r][c]=false;
    }

    // Tmeeno 모양은 Dfs로 불가하기 때문에 따로 설정해줘야함
    /**
     *
     * @param r : 가운데 있는 애의 행
     * @param c : 가운데 있는 애의 열
     */
    private static void tmeeno(int r, int c){
        int sum = map[r][c];
        int cnt = 0;

        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(isInRange(nr, nc)){
                sum += map[nr][nc];
                cnt++;
            }
        }
        

        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(isInRange(nr, nc)){
                if(cnt==3){
                    answer = Math.max(answer, sum);
                }
                else{
                    answer = Math.max(answer, sum-map[nr][nc]);
                }
            }
        }
    }

    private static boolean isInRange(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }
}
