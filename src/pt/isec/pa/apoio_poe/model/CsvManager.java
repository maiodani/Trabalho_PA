package pt.isec.pa.apoio_poe.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvManager {
    public static Scanner getScanner(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            return sc;
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        return null;
    }

    public static List<String> readFile(String fileName){
        List<String> aux = new ArrayList<>();
        List<String> data = new ArrayList<>();
        Scanner sc = getScanner(fileName);
        if(sc!=null){
            sc.useDelimiter(",");
            while(sc.hasNext()){
                aux.add(sc.next());
            }
            for(String s:aux){
                String[] d = s.split("\n"); //RETIRAR O \N QUANDO HA MUDANÇA DE LINHA
                for(String ss: d)data.add(ss);
            }
            sc.close();
            return data;
        }else{
            System.out.println("ERRO AO LER O FICHEIRO");
        }
        sc.close();
        return null;
    }
}
