package pt.isec.pa.apoio_poe.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvManager {
    public static Scanner getScanner(String fileName) {

        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sc;
    }

    public static String[][] readFile(String fileName) {
        List<String> getData = new ArrayList<>();
        List<String> aux = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        int i = 0;
        int acumulador = 0;
        Scanner sc = getScanner(fileName);
        if(sc!=null){
            sc.useDelimiter(",");

            while(sc.hasNext()){
                getData.add(sc.next());
            }
            for(String s : getData){
                String[] d = s.split("\\r?\\n"); //RETIRAR O \N QUANDO HA MUDANÇA DE LINHA
                aux.add(d[0]);
                i++;
                if (d.length == 2){
                    num.add(i);
                    i = 1;
                    aux.add(d[1]);
                }
            }
            num.add(i);
            String [][] data = new String[num.size()][];
            for (int j = 0; j < data.length; j++) {
                data[j] = new String[num.get(j)];
                for (int k = 0; k < data[j].length; k++) {
                    data[j][k] = aux.get(acumulador+k);
                }
                acumulador+=num.get(j);
            }
            sc.close();
            return data;
        }
        try{
            sc.close();
        }catch (NullPointerException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String writeFile(String filename, StringBuilder data){
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.write(data.toString());
        } catch (FileNotFoundException e) {
            data.delete(0, data.length()-1);
            data.append(e.getMessage());
        }
        data.delete(0, data.length());
        return data.toString();
    }
}
