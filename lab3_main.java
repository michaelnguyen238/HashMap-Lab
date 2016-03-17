import java.io.*;
import java.util.Scanner;

/**
 * Created by Michael Nguyen on 3/7/16.
 */
public class lab3_main {
    public static void main(String[] args) {

        HashMap<String,Integer> myMap = new HashMap<String, Integer>(509);

        String file = "players_homeruns.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] players = line.split(cvsSplitBy);
                myMap.insert(players[0], Integer.parseInt(players[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Begin program

        Scanner sn = new Scanner(System.in);
        boolean cont = true;
        while (cont) {
            System.out.print("Enter a player name: ");
            String name = sn.nextLine();
            if (name.equals("exit")) {
                cont = false;
            } else {
                try {
                    System.out.println("Homeruns: " + myMap.find(name));
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
