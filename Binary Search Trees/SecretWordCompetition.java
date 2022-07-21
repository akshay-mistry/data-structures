import java.util.HashMap;

public class SecretWordCompetition {

    public SecretWordCompetition () {

        String str = "21en ilni tidn ifuo yneh wree hcll idna retn eceh tmor fgno lsre ttel xisl";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Integer val = map.get(c);
            if (val != null) {
                map.put(c, val + 1);
            }
            else {
                map.put(c, 1);
            }
        }

        System.out.print(map);

//ttearosh
    }


    public static void main (String[] args) {
        SecretWordCompetition secretWordCompetition = new SecretWordCompetition();
    }
}
