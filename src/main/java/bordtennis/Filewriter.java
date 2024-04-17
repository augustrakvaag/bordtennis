package bordtennis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Filewriter {

    String filename = "src/main/java/bordtennis/table.txt";

    public List<String> getTable() {
        List<String> values = new ArrayList<String>();
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            
            while(scanner.hasNextLine()){
                String data =  scanner.nextLine();
                values.add(data);
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        return values;
    }

    public void UpdatePoints(Map<String,List<Integer>> table){
        try {
            FileWriter fw = new FileWriter(filename);
            for(String player : table.keySet()){
                String output = player;
                output += "," + table.get(player).get(0) + "," + table.get(player).get(1);
                fw.write(output + "\n");
            }
            fw.close();
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void setFilename(String filename){
        this.filename = filename;
    }
}