/*
문제
n개의 식당에 있는 고객들의 체온 측정
검사자: 검사팀장, 검사팀원
팀장, 팀원이 검사할 수 있는 고객의 수 다름
한 가게 당 팀장은 무조건 1명(0명, 2명 이상 X), 팀원은 여러명 가능
담당한 가게에 대해서만 검사
n개의 식당 고객들의 체온 측정 위해 필요한 검사자 수의 최솟값은?

입력
첫째 줄: 식당 수 n
둘째 줄: 각 식당에 있는 고객 수
셋째 줄: 검사팀장, 검사팀원이 검사할 수 있는 최대 고객 수

조건
1 ≤ n ≤ 1,000,000
1 ≤ (각 식당에 있는 고객의 수) ≤ 1,000,000
1 ≤ (팀장 혹은 팀원 한 명이 검사 가능한 최대 고객의 수) ≤ 1,000,000
*/
import java.io.*;
import java.util.*;
public class Main {

    static int N, manager, teammate;
    static int[] arr;

    public static void main(String[] args) throws IOException{

        long peopleCnt = 0;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        manager = Integer.parseInt(st.nextToken());
        teammate = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for(int person : arr) {
            cnt = 1; // 무조건 manager는 1명 있어야 함
            person -= manager; // manager 검사 후 남은 사람들
            if(person > 0) {
                cnt += person/teammate;
                if(person%teammate > 0) // 남은 사람들이 있으면 팀원 1명 더 필요함
                    cnt++;
            }
            peopleCnt += cnt;
        }

        System.out.println(peopleCnt);

    }
}