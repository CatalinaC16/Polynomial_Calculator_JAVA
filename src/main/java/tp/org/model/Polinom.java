package tp.org.model;

import java.util.*;
import java.util.regex.*;

public class Polinom {
 // in aceasta variabila vor fi adaugati coeficientii si gradele polinomului creat
   private HashMap<Integer, Double> map;
   public Polinom() {
       map = new HashMap<>();
   }

   public void adaugaPolinomLaHashMap(String polinom) {
     /// stabilim formatul si apelam metoda matcher care va returna un obiect
     /// pe care il folosim pentru a cauta potriviri in sirul de caractere primit ca paramatru
       Pattern format = Pattern.compile("([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)?x\\^(\\d+)|(?:\\d+(?:\\.\\d*)?|\\.\\d+)x|(?:\\d+(?:\\.\\d*)?)|(?:x\\^\\d+)|(?:x)))");
       Matcher matcher = format.matcher(polinom);

    //Instructiunile din interiorul blocului while parcurg toate potrivirile gasite de obiectul Matcher,
    //extrag coeficientul si gradul din fiecare termen si il adauga in HashMap.

       while (matcher.find()) {
           int grad = 0;
           double coef = 0.0, semn = 1.0;
           String termen = matcher.group();
           if (termen.startsWith("-")) {
               semn = -1.0;
               termen = termen.substring(1);
           } else if (termen.startsWith("+")) {
               termen = termen.substring(1);
           }

           if (termen.matches("\\d+x\\^\\d+")) {
               String[] parts = termen.split("x\\^");
               coef = Double.parseDouble(parts[0]);
               grad = Integer.parseInt(parts[1]);
           } else if (termen.matches("\\d+x")) {
               String[] parts = termen.split("x");
               coef = Double.parseDouble(parts[0]);
               grad = 1;
           } else if (termen.matches("x\\^\\d+")) {
               String[] parts = termen.split("x\\^");
               coef = 1.0;
               grad = Integer.parseInt(parts[1]);
           } else if (termen.matches("x")) {
               coef = 1.0;
               grad = 1;
           } else if (termen.matches("\\d+")) {
               coef = Double.parseDouble(termen);
               grad = 0;
           }

           this.getMap().put(grad, this.getMap().getOrDefault(grad, 0.0) + (semn * coef));
       }
    }
    ///retuneaza gradul maxim din HashMap-ul polinomului
    public int getMaxGrad(){

       int maxGrad = -1;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            Integer key = entry.getKey();
            if(maxGrad < key)
                maxGrad = key;
        }
        return maxGrad;
    }
    public void getStringMap() {
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();
            System.out.println("Grad: " + key + " Coeficient: " + value);
        }
    }

    //afisare user-friendly
    public String toString(){
        String str = "";
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(map.entrySet());
        ListIterator<Map.Entry<Integer, Double>> iterator = list.listIterator(list.size());
        int i=0;
        while (iterator.hasPrevious()) {
            Map.Entry<Integer, Double> entry = iterator.previous();
            Integer grad = entry.getKey();
            Double coef = entry.getValue();
            if(grad == 0) {
                if(coef>0)
                    if(i==0)
                        str = str + coef;
                    else
                        str = str +"+"+ coef;
                else if(coef<0)
                    str = str + coef;
            }else
                if (grad == 1){
                    if(coef>0)
                        if(i==0)
                            str = str + coef+"x";
                        else
                            str = str +"+"+ coef+"x";
                    else if(coef<0)
                        str = str + coef+"x";
                }
               else
                {
                    if(coef>0)
                        if (i == 0)
                            if (coef == 1)
                                str = str + "x^" + grad;
                            else
                                str = str + coef + "x^" + grad;
                        else
                            str = str + "+" + coef + "x^" + grad;
                   else if(coef<0)
                        str = str + coef + "x^" + grad;
                }
            i++;
        }
        if(str.length()==0)
            str="0.0";
        return str;
    }
    public HashMap<Integer, Double> getMap() {
        return map;
    }
    public void setMap(HashMap<Integer, Double> map) {
        this.map = map;
    }
}
