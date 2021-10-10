import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<symbols> sy = new ArrayList<>();
        Scanner sc = new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Enter symbol (if you finished enter .) :");
        String data = sc.next();
        while (data.charAt(0) != '.') {
            System.out.print("Enter prop :");
            Double f = sc.nextDouble();
            sy.add(new symbols(data.charAt(0), f));
            System.out.print("Enter symbol (if you finished enter .) :");
            data = sc.next();

        }

        Double low = 0.0;
        for (int i = 0; i < sy.size(); i++) {
            Double high = sy.get(i).getFreq() + low;
            sy.get(i).setLowerfreq(low);
            sy.get(i).setUpperfreq(high);
            low = high;
        }

        Double lower = 0.0;
        Double upper = 1.0;
        Double range;

        System.out.print("Enter String :");
        Scanner a = new Scanner(System.in);
        String input = a.nextLine();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            for (int j = 0; j < sy.size(); j++) {

                // if found in array
                if (sy.get(j).getSymbol() == c) {
                    range = upper - lower;
                    upper = lower + range * sy.get(j).getUpperfreq();
                    // to keep the previous lower
                    lower = lower + range * sy.get(j).getLowerfreq();
                    System.out.println(c);
                    System.out.println(lower);
                    System.out.println(upper);
                    break;

                }
            }

        }
        Double code = (upper + lower) / 2;
        System.out.println("code is : " + code);

        System.out.println("Decompression");
        for (int i = 0; i < input.length(); i++) {

            for (int j = 0; j < sy.size(); j++) {
                if (code > sy.get(j).getLowerfreq() && code < sy.get(j).getUpperfreq()) {
                    System.out.println(sy.get(j).getSymbol());
                    // get the new code
                    code = (code - sy.get(j).getLowerfreq()) / (sy.get(j).getUpperfreq() - sy.get(j).getLowerfreq());
                    break;
                }

            }
        }

    }

}
