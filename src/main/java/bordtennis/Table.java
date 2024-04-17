package bordtennis;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Table {

    Filewriter filewriter;
    Map<String,List<Integer>> table = new HashMap<String,List<Integer>>();

    public void getTable(){
        List<String> tableList = filewriter.getTable();
        for(String entry : tableList){
            List<Integer> data = new ArrayList<>();
            String[] values = entry.split(",");
            String name = values[1];
            int matches = Integer.parseInt(values[2]);
            int points = Integer.parseInt(values[3]);
            data.add(matches,points);
            this.table.put(name, data);
        }
    }

    public void printTable(){
        for(String player : table.keySet()){
            String output = player;
            output += " " + table.get(player).get(0) + " " + table.get(player).get(1);
            System.out.println(output);
        }
    }

    public void addMatch(){
        getTable();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter winner: ");
        String winner = in.nextLine();
        System.out.println("Enter loser: ");
        String loser = in.nextLine();
        in.close();

        List<Integer> winnerValues = this.table.get(winner);
        Integer winnerMatchesPlayed = winnerValues.get(0);
        Integer winnerPoints = winnerValues.get(1);
        winnerMatchesPlayed++;
        winnerPoints++;

        List<Integer> loserValues = this.table.get(loser);
        Integer loserMatchesPlayed = loserValues.get(0);
        loserMatchesPlayed++;
    }
}
