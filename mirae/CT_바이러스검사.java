package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CT_바이러스검사 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] customers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            customers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int LD = Integer.parseInt(st.nextToken());
        int MBR = Integer.parseInt(st.nextToken());

        long cnt = 0;
        for(int i=0; i<N; i++) {
            cnt += 1;
            customers[i] -= LD;
            if(customers[i] <= 0) continue;
            cnt += (int) Math.ceil(customers[i]/(double)MBR);
        }
        System.out.println(cnt);
	}

}
