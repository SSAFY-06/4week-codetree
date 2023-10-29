/*
문제
n*m 크기 이차원 영역에 방화벽 설치 - 불로 인한 피해 최소화
불 - 상하조우로 인접한 공간으로 번짐, 방화벽은 뚫을 수 없음
방화벽은 불이 있는 위치에 설치 불가능
추가로 3개의 방화벽을 설치할 수 있을 때, 최대로 불이 퍼지지 않는 영역의 크기는?

입력
첫째 줄: n, m
n개 줄: 맵 정보(불: 2, 방화벽: 1, 빈 칸: 0)

제한
3 ≤ n, m ≤ 8
2 ≤ 총 불의 개수 ≤ 10
3 ≤ 총 빈 칸의 개수

아이디어
중복 없이, 순서 상관 없이 빈 칸 중 3곳 골라서 방화벽 설치 - 조합, 64C3
방화벽을 세운 후 불이 4방으로 모두 확산 후 빈 칸 세기
*/
import java.io.*;
import java.util.*;
public class Main {

    static int N, M, maxSpace = 0;
    static int[][] arr;
    static int[] selected;
    static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr= new int[N][M];
        selected = new int[3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0); // 조합 고르기
        System.out.println(maxSpace);

    }

    static void comb(int idx, int cnt) {
        if(cnt==N*M || idx==3) { // 배열 최대 index 또는 3개 이미 고른 경우
            if(idx<3) return; // 배열 모두 돌았는데 숫자를 3개 미만으로 고르면 return
            for(int i : selected) {
                if(arr[i/M][i%M]!=0) // 선택한 지역이 빈 칸이 아닌 경우 return
                    return;
            }
            spread(); // 선택한 방화벽 설치 지역이 합당한 경우 불 확산 확인
            return;
        }
        selected[idx] = cnt;
        comb(idx+1, cnt+1); // 현재 값을 선택한 경우
        comb(idx, cnt+1); // 현재 값을 선택하지 않은 경우
    }

    static void spread() { // 불의 확산
        int cnt = 0; // 빈 칸 세기
        int[][] copy = new int[N][M]; // 불이 확산된 곳은 불 표시 - 원본 배열 보존
        Queue<int[]> q = new ArrayDeque<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copy[i][j] = arr[i][j]; // 원본 배열 복사
                if(copy[i][j]==2) q.add(new int[] {i, j}); // 불이 시작하는 공간이 2곳 이상 가능 - 모두 queue에 넣기(BFS 탐색)
            }
        }

        for(int sel : selected) { // 선택한 방화벽 설치 지점에 방화벽 설치
            copy[sel/M][sel%M] = 1;
        }

        while(!q.isEmpty()) { // 불 확산 가능할 때까지 확산
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            for(int d=0; d<4; d++) {
                int dr = r+deltas[d][0];
                int dc = c+deltas[d][1];
                if(dr<0 || dr>=N || dc<0 || dc>=M || copy[dr][dc]!=0) continue; // 범위 밖이거나 빈 칸이 아니면 통과
                copy[dr][dc] = 2; // 불 확산 가능한 곳이면 화재 지점으로 변경
                q.add(new int[] {dr, dc});
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(copy[i][j]==0) cnt++; // 전체 확산 후 빈 칸 확인
            }
        }

        maxSpace = Math.max(maxSpace, cnt); // 최대 빈 칸 계산

    }

}