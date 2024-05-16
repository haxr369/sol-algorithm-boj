import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * IP 주소는 네 개의 바이트로 구성되어 있으며, 각각을 10진수로 나타내고(앞에 0을 붙이지 않은 형태로) 사이에 점을 찍어 주소를 표현
 * 바이트이기 때문에 각각의 수는 0부터 255까지의 값을 갖게 된다
 *
 * IP 네트워크는 ‘네트워크 주소’와 ‘네트워크 마스크’라는 두 개의 정보
 *
 *
 *  즉, 각각의 바이트를 8자리의 이진수로 나타내고,
 *      네 개 붙여놓은(앞에서부터) 32자리의 이진수를 생각해 보자
 *
 *  IP 주소 = 4개 바이트 = 각각을 8자리 이진수 x 4 = 32자리 이진수
 *
 *  IP 네트워크에는 기본적으로 2^m 개의 컴퓨터(혹은 IP 주소)가 할당
 *  네트워크 주소는 앞의 32-m 자리가 임의의 수(0 또는 1)로 구성되어 있고,
 *  뒤의 m자리는 0으로 채워지게 된다 xxx~xxxx0000 => 총 32자리 이진수
 *  IP 네트워크에는 앞의 32-m 자리가 네트워크 주소와 일치하는 모든 IP들이 포함
 *
 *  네트워크 주소가 194.85.160.176
 *  네트워크 마스크가 255.255.255.248 => 1111 1111(255), 1111 1111(255), 1111 1111(255), 1111 1000(248)
 *
 *               194.85.160.176 =>                                                10110 000 -> 176~183
 *                                                                                10110 111 ->
 *  이 네트워크는 194.85.160.176부터 194.85.160.183까지의 8개의 IP 주소가 포함
 *
 *  답이 여러 개인 경우에는 가장 크기가 작은(포함되는 IP 주소가 가장 적은, 즉 m이 최소인) 네트워크를 구하도록 한다.
 *
 *  1. 제일 큰 것과 제일 작은 것의 차이가 처음 나는 비트 위치(idx)를 구하기 ex) 2
 *  2. 네트워크 마스크 구하기 = idx 앞까지 전부 1인 네트워크 주소 구하기 ex) 255.255.255.248
 *  3. 네트워크 주소 구하기 = 첫번째 네트워크에서 idx 이하 인덱스를 0으로 바꾸기 ex) 194.85.160.176
 *
 *  2인수와 10진수를 맵핑 필요.
 *
 *  8진수 4개 -> 길이가 4개인 비트마스크 이용 = 네트워크 주소 하나 표현
 */

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] ips = new long[1004];
        for(int i=0; i<N; i++){
            String[] strings = br.readLine().split("\\.");
            for(int j=0; j<4; j++){
                Long l = Long.parseLong(strings[j]);
//                System.out.println("l : "+l);
                for(int h=0; h<8; h++){
                    if(((l<<(j*8)) & (1L<<(j*8+h))) != 0){
                        ips[i] |= (1L<<(j*8 + h));
                    }
                }
            }
        }

        int prefixIdx = findPrefixIdx(ips, N);
//        System.out.println("prefixIdx : "+prefixIdx);
        long ip=ips[0];
        long mask = 0;
//        int i=0;
//        while(i<32){
//            // ip는 유지, mask는 1

//            i++;
//        }
        for(int i=0; i<4; i++){
            for(int j=7; j>=0; j--){
                int idx = i*8 + j;
                if(idx<prefixIdx){
                    mask |= (1L<<(i*8+7-j)); // 번호 인덱스
                }
                else{
                    // mask는 0 유지 ip는 모두 0으로 설정
                    ip &= (~(1L<<(i*8+7-j)));
                }
            }
        }
        System.out.println(printNetwork(ip));
        System.out.println(printNetwork(mask));
    }

    private static String printNetwork(long mask) {
//        System.out.println("mask : "+mask);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++){
            long m = 0;
            for(int j=0; j<8; j++){
//                sb.append((mask&(1L<<(i*8+j))) == 0 ? '0' : '1');
                m |= (1L<<(i*8+j));
            }
//            System.out.println("mask i:j "+m);
//            System.out.println(mask&m);
            sb.append((mask&m)>>(i*8));
            if(i!=3){
                sb.append('.');
            }
        }
        return sb.toString();
    }

    private static String printBi(long l){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++){
            for(int j=7; j>=0; j--){
                sb.append((l&(1L<<(i*8+j))) == 0 ? '0' : '1');
            }
//            System.out.println("mask i:j "+m);
//            System.out.println(mask&m);
            if(i!=3){
                sb.append('.');
            }
        }
        return sb.toString();
    }

    private static int findPrefixIdx(long[] ips, int N) {

        for(int i=0; i<4; i++){
            for(int j=7; j>=0; j--){
                int idx = i*8 + j; // ip real 인덱스, ip numb 인덱스 = i*8+(7-j)
                for(int n=0; n<N; n++){
//                    System.out.print((ips[n]&(1L<<idx))>>idx);
                    if((ips[n]&(1L<<idx)) != (ips[0]&(1L<<idx))){
//                        System.out.println("n :"+n+" ip idx "+(i*8+(7-j))+"에서 다릅니다.");
//                        System.out.println("ip : " + printNetwork(ips[0])+" "+printBi(ips[0]));
//                        System.out.println(" ip : " + printNetwork(ips[n])+" "+printBi(ips[n]));
                        return i*8+ (7-j);
                    }

                }
            }
        }
        return 32;
    }

}

/*

3
255.255.27.192
255.255.29.192
255.255.28.0

3
194.85.27.192
194.85.29.192
194.85.60.0

3
194.85.155.192
194.85.157.192
194.85.188.0

3
255.255.255.255
254.255.255.255
239.255.255.255

1
174.23.243.1
174.23.243.1
255.255.255.255

2
142.23.132.91
142.23.132.92

2
142.91.132.91
142.92.132.92
 */