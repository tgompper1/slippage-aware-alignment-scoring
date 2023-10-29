/* Tess Gompper */

public class slippageAwareAlignment{
    public static void main(String[] args){
        // String S = "TCATAAATAATTTTGCTTGCTGAAGGAAGAAAAAGTGTTTTTCATAAACCCATTATCCAGGACTGTTTATAGCTGTTGGAAGGACTAGGTCTTCCCTAGCCCCCCCAGTGTGCAAGGGCAGTGAAGACTTGATTGTACAAAATACGTTTTGTAAATGTTGTGCTGTTAACACTGCAAATAAACTTGGTAGCAAACACTTC";
        // String T = "TACTTCATAAATAATTTTGCTTGCTGAAGGAAGAGAACATATTTTTCATGAACTCATAATCTTGGACTGTTTATAGATATTGGGGGGAGTAGGTCTTCTCTGGCCCCCCCCAGTATGCAAGGGCAATGGAGACCTGATTATATAAAGTATGTTTATAAATGCTGTGCTGTTAATACTGCAAATAAACTAAATAGCAAACA";
        
        String S ="TTCAGGCTGTTGTTGGCTTAGGGCTGGAAGCACAGAGTGGCTTGGCCTCAAGAGAATAGCTGGTTTCCCTAAGTTTACTTCTCTAAAACCCTGTGTTCACAAAGGCAGAGAGTCAGACCCTTCAATGGAAGGAGAGTGCTTGGGATCGATTATGTGACTTAAAGTCAGAATAGTCCTTGGGCAGTTCTCAAATGTTGGAGTGGAACATTGGGGAGGAAATTCTGAGGCAGGTATTAGAAATGAAAAGGAAACTTGAAACCTGGGCATGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCAAGGTGGGCAGATCACTGGAGGTCAGGAGTTCGAAACCAGCCTGGCCAACATGGTGAAACCCCATCTCTACTAAAAATACAGAAATTAGCCGGTCATGGTGGTGGACACCTGTAATCCCAGCTACTCAGGTGGCTAAGGCAGGAGAATCACTTCAGCCCGGGAGGTGGAGGTTGCAGTGAGCCAAGATCATACCACGGCACTCCAGCCTGGGTGACAGTGAGACTGTGGCTCAAAAAAAAAAAAAAAAAAAGGAAAATGAAACTAGAAGAGATTTCTAAAAGTCTGAGATATATTTGCTAGATTTCTAAAGAATGTGTTCTAAAACAGCAGAAGATTTTCAAGAACCGGTTTCCAAAGACAGTCTTCTAATTCCTCATTAGTAATAAGTAAAATGTTTATTGTTGTAGCTCTGGTATATAATCCATTCCTCTTAAAATATAAGACCTCTGGCATGAATATTTCATATCTATAAAATGACAGATCCCACCAGGAAGGAAGCTGTTGCTTTCTTTGAGGTGATTTTTTTCCTTTGCTCCCTGTTGCTGAAACCATACAGCTTCATAAATAATTTTGCTTGCTGAAGGAAGAAAAAGTGTTTTTCATAAACCCATTATCCAGGACTGTTTATAGCTGTTGGAAGGACTAGGTCTTCCCTAGCCCCCCCAGTGTGCAAGGGCAGTGAAGACTTGATTGTACAAAATACGTTTTGTAAATGTTGTGCTGTTAACACTGCAAATAAACTTGGTAGCAAACACTTC";
        String T = "TAAGAAATGGTCTTCCATGCCCGGGGAGCACCTCTCAGTCTTTGGTGCTTCCACACCCTACTTACTAAGTATTTTTCATGCATCCGCCTGGAAAAACCAGCTTCTACCTATGTGAGGGTGGGTGCCTTAAAGGTTTTCTAACCAAGTCCCCCTTTGAAGTCTGTCTTGAACACAAAAAGTTTTCACCTGAGAAAGCTTTATCCCACCAATTGAGCAAGATGCTAATCTGTTGCATATCGTTCTTGGAGAGACAGTAGAGTGGCTTGGCTGATCTGAGCTGGGCATGGTGGTACTTGCCTATAATCACAGCATTTGGTGGAAAGAGATGGGCATGTCGTGAGTTGTATATGACTGAAGAACAAATAGCTAGTGGTCCTGATTTGTTCCTTATAAAATTGCATGTTCATAAAGGCCAAGAGTCAAGTCCTTCAAGGAGATGGCTTGAGATCTGTGGTTTGACTTAAAATCAGTGCTCCTCAAGGCTCCAGGTTTAGTAGCCAGCACCAAACAACACCCAGAATAGGGTTTGTGCAGGACACTGAGGAAATTCAGAGCCAGATTATGTATTTGATGTGAATGTGAGGTTTTTCAAGTGCCAGTGTCAAGGAGAGCCTTCTAATTCCCCATAGCTTATGAGCAGTGTATCTGTTGTAGCAGCTCTAAGTAACCCATTCTTCTTTAAATACACGGACTCTGATATGAATATTTCATGTCTGTACAATGATGGATTCCACCAGGAAAGGAGTTGTTGCTTTCTTTGAAGTAATTTCTTCCCTTTTGCTCCCTGTTGCTGAGGCATACTACTTCATAAATAATTTTGCTTGCTGAAGGAAGAGAACATATTTTTCATGAACTCATAATCTTGGACTGTTTATAGATATTGGGGGGAGTAGGTCTTCTCTGGCCCCCCCCAGTATGCAAGGGCAATGGAGACCTGATTATATAAAGTATGTTTATAAATGCTGTGCTGTTAATACTGCAAATAAACTAAATAGCAAACA";
        
        int cs = -1;
        int cn = -2;
        int[][] c = new int[4][4];

        c[0][0] = 1;
        c[0][1] = -1;
        c[0][2] = -1;
        c[0][3] = -1;

        c[1][0] = -1;
        c[1][1] = 1;
        c[1][2] = -1;
        c[1][3] = -1;

        c[2][0] = -1;
        c[2][1] = -1;
        c[2][2] = 1;
        c[2][3] = -1;

        c[3][0] = -1;
        c[3][1] = -1;
        c[3][2] = -1;
        c[3][3] = 1;

        // for (int[] x : c)
        //     {
        //     for (int y : x)
        //     {
        //             System.out.print(y + " ");
        //     }
        //     System.out.println();
        //     }
        calc(S,T, c, cs, cn);
    }

    public static void calc(String S, String T, int[][] c, int cs, int cn){
        S = "-"+S;
        T = "-" + T;
        int n = S.length();
        int m = T.length();
        int[][] opt = new int[n][m];
        int x=0;
        

        // fill optimal alignment matrix
        opt[0][0] = 0;
        
        for(int i = 1; i < n; i++){
            if(S.charAt(i) == S.charAt(i-1)){
                x = x + cs;
            }else{
                x = x + cn;
            }
            opt[i][0] = x;
        }
        
        for(int i = 1; i < m; i++){
            if(T.charAt(i) == T.charAt(i-1)){
                x = x + cs;
            }else{
                x = x + cn;
            }
            opt[0][i] = x;
        }
        int match = 0;
        int insertion = 0;
        int deletion = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                match = opt[i-1][j-1] + getSubCost(c, S.charAt(i), T.charAt(j));
                if(T.charAt(j)==T.charAt(j-1)){
                    insertion = opt[i][j-1] + cs;
                } else{
                    insertion = opt[i][j-1] + cn; 
                }
                if(S.charAt(i)==S.charAt(i-1)){
                    deletion = opt[i-1][j] + cs;
                } else{
                    deletion = opt[i-1][j] + cn; 
                }
                opt[i][j] = myMax(match, insertion, deletion);
            }
        }

        String aS = "";
        String aT = "";

        int score = opt[n-1][m-1];
        int i = n-1;
        int j = m-1; 
        
        while(i > 0 || j > 0){
            if(i > 0 && j > 0 && opt[i][j] == opt[i-1][j-1]+getSubCost(c, S.charAt(i), T.charAt(j))){
                aS = S.charAt(i) + aS;
                aT = T.charAt(j) + aT;
                i--;
                j--;
            }else if(i>0 && (opt[i][j] == opt[i-1][j] + cs || opt[i][j] == opt[i-1][j] + cn)){
                aS = S.charAt(i) + aS;
                aT = "-" + aT;
                i--;
            }else{
                aS = "-" + aS;
                aT = T.charAt(j) + aT;
                j--;
            }
        }

        System.out.println("score: " + score);
        System.out.println("human: " + aS);
        System.out.println("mouse: " + aT);
    }   

    public static int getSubCost(int[][] c, char s, char t){
        int i = 0;
        int j = 0;
        switch(s){
            case 'A':
                i = 0;
                break;
            case 'C':
                i = 1;
                break;
            case 'G':
                i = 2;
                break;
            case 'T':
                i = 3;
                break;
        }
        switch(t){
            case 'A':
                j = 0;
                break;
            case 'C':
                j = 1;
                break;
            case 'G':
                j = 2;
                break;
            case 'T':
                j = 3;
                break;
        }
        return c[i][j];
    }

    public static int myMax(int m, int i, int d){
        if (m >= i && m >= d)
            return m;
        else if (i >= m && i >= d)
            return i;
        else
            return d;
    }
}
