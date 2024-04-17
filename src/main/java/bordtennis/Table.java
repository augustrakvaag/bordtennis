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
            String name = values[0];
            int matches = Integer.parseInt(values[1]);
            int points = Integer.parseInt(values[2]);
            data.add(matches);
            data.add(points);
            this.table.put(name, data);
        }
    }

    public void printTable(){
        System.out.println("\nSpiller  Kamper  Poeng");
        for(String player : table.keySet()){
            String output = player;
            output += "      " + table.get(player).get(0) + "      " + table.get(player).get(1);
            System.out.println(output);
        }
        System.out.println("\n");
    }

    public void addMatch(Scanner in){
        getTable();
        System.out.println("Enter winner: ");
        String winner = in.nextLine();
        System.out.println("Enter loser: ");
        String loser = in.nextLine();

        if(!table.keySet().contains(winner)){
            List<Integer> newPlayer = new ArrayList<>();
            newPlayer.add(0);
            newPlayer.add(0);
            table.put(winner, newPlayer);
        }
        List<Integer> winnerValues = this.table.get(winner);
        Integer winnerMatchesPlayed = winnerValues.get(0);
        Integer winnerPoints = winnerValues.get(1);
        winnerMatchesPlayed++;
        winnerPoints++;
        winnerValues.set(0, winnerMatchesPlayed);
        winnerValues.set(1, winnerPoints);

        if(!table.keySet().contains(loser)){
            List<Integer> newPlayer = new ArrayList<>();
            newPlayer.add(0);
            newPlayer.add(0);
            table.put(loser, newPlayer);
        }
        List<Integer> loserValues = this.table.get(loser);
        Integer loserMatchesPlayed = loserValues.get(0);
        loserMatchesPlayed++;
        loserValues.set(0, loserMatchesPlayed);
        filewriter.UpdatePoints(this.table);
    }

    public static void main(String[] args) {
        Table t = new Table();
        t.filewriter = new Filewriter();
        Scanner in = new Scanner(System.in);
        while(true){
            t.getTable();
            System.out.println("Add match (A) or view table (W)");
            String choice = in.nextLine();
            if(choice.equals("A")){
                t.addMatch(in);
            }
            if(choice.equals("W")){
                t.printTable();
            }
            if(choice.equals("Q")){
                break;
            }
        }
        in.close();
    }
}
