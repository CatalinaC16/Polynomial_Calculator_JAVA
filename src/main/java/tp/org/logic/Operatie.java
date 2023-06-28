package tp.org.logic;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import tp.org.model.Polinom;
public class Operatie {

    //Punem valorile primului polinom intr-un nou polinom si parcurgem valorile din poli2,
    //se aduna coeficientii pentru gradele comune celor două si se pun neschimbati cei necomuni.
    public Polinom adunare(Polinom poli1,Polinom poli2) {
        Integer exp;
        Polinom adun = new Polinom();
        adun.getMap().putAll(poli1.getMap());

        for (Map.Entry<Integer, Double> entry : poli2.getMap().entrySet()){
            exp = entry.getKey();
            if( adun.getMap().get(exp)!=null)
                adun.getMap().replace(exp,adun.getMap().get(exp)+poli2.getMap().get(exp));
            else
                adun.getMap().put(exp,poli2.getMap().get(exp));
        }
        return adun;
    }
    //Punem valorile primului polinom într-un nou polinom și parcurgem valorile din poli2,
    // se scad coeficienții pentru gradele comune celor două și se pun cu semn schimbat cei necomuni (*(-1)).
    public Polinom scadere(Polinom poli1,Polinom poli2) {
        Integer exp;
        Polinom scad = new Polinom();
        scad.getMap().putAll(poli1.getMap());

        for (Map.Entry<Integer, Double> entry : poli2.getMap().entrySet()){
            exp = entry.getKey();
            if(scad.getMap().get(exp)!=null)
               scad.getMap().replace(exp,scad.getMap().get(exp)-poli2.getMap().get(exp));
            else
                scad.getMap().put(exp,-poli2.getMap().get(exp));
        }
        return scad;
    }

    /*
    Inmultirea a doua polinoame presupune ca fiecare element dintr-un polinom
      sa fie inmultit cu fiecare element din celalalt element.
      Astfel, parcurgem cele doua polinoame deodata si exp = i+j, daca exponentul exista adunam
      si coeficientul obtinut la acest pas, iar daca nu exista punem coeficientul.
    */

    public Polinom inmultire(Polinom poli1,Polinom poli2) {
        Polinom multi = new Polinom();
        for (Integer i  : poli1.getMap().keySet()) {
            for (Integer j  : poli2.getMap().keySet()) {

                Integer exp = i+j;
                Double coef = poli1.getMap().get(i)* poli2.getMap().get(j);
                if (multi.getMap().containsKey(exp)) {
                    multi.getMap().replace(exp, multi.getMap().get(exp) + coef);
                } else {
                    multi.getMap().put(exp, coef);
                }
            }
        }
        return multi;
    }
    /*
      Metoda de impartire trebuie sa returneze un vector de Polinoame, 2 mai exact, rezultatul
      si restul impartirii . Pentru fiecare polinom stabilim din start gradul maxim si daca polinomul
      2 are gradul maxim mai mare decât polinomul1 atunci operatia nu este posibila.
      Cat timp gradul maxim al primului este mai mare decat gradul celui de-al doilea polinom
      si sunt diferite de 0, atunci impartim cei doi coeficienti(pentru exp cu grad maxim) si punem
      in polinomul de impartire coeficientul la diferenta exponentilor. Apoi inmultim fiecare elememt din polinomul
      de impartire cu polinomul 2 si dupa le scadem din polinomul 1 si repetam pasii. La iesire din while in pol1 va
      fi restul impartirii
     */
    public Polinom[] impartire(Polinom poli1, Polinom poli2) {
        Polinom[] rezultat = new Polinom[2];
        Polinom poliImpartire = new Polinom();
        Polinom poliRest = new Polinom();
        Polinom pol1 = new Polinom();pol1.getMap().putAll(poli1.getMap());
        Polinom pol2 = new Polinom();pol2.getMap().putAll(poli2.getMap());
        Polinom produs = new Polinom();

        int gradMax1 = pol1.getMaxGrad();
        int gradMax2 = pol2.getMaxGrad();
        if (gradMax1 < gradMax2 || gradMax2 == 0 || gradMax1 == 0) {
            rezultat[0] = poliImpartire;
            rezultat[1] = poliRest;
            return rezultat;
        }
        while(gradMax1>=gradMax2 && gradMax2!=0 && gradMax1!=0){
            produs.getMap().clear();
            double coef = pol1.getMap().get(gradMax1) /pol2.getMap().get(gradMax2);
            poliImpartire.getMap().put(gradMax1 - gradMax2,coef);

            for (Integer grad : pol2.getMap().keySet()) {
                double coef2 = coef * pol2.getMap().get(grad);
                produs.getMap().put(grad + gradMax1 - gradMax2, coef2);
            }

            for (Integer degree : produs.getMap().keySet()) {
                if (pol1.getMap().containsKey(degree)) {
                    double coeff = pol1.getMap().get(degree) - produs.getMap().get(degree);
                    if (coeff == 0)
                        pol1.getMap().remove(degree);
                    else
                        pol1.getMap().put(degree, coeff);
                }
                else
                    pol1.getMap().put(degree, -produs.getMap().get(degree));
            }
            gradMax1 = pol1.getMaxGrad();
        }

        rezultat[0] = poliImpartire;
        rezultat[1] = pol1;
        return rezultat;
    }
    /*
    Derivarea la polinoame presupune ca pentru fiecare element din polinom, coeficientul
    sa ia valoarea exponent*coeficient actual si exp--, in cazul in care exp>0.
     */
    public Polinom derivare(Polinom poli){
        Polinom poliDerivat = new Polinom();
        for(Integer i : poli.getMap().keySet()){
            Integer exp = i;
            if(exp>0)
                exp--;
            Double coef = poli.getMap().get(i);
            Double coef2 = i * coef;
            poliDerivat.getMap().put(exp,coef2);
        }
        return poliDerivat;
    }
    /*
    Integrarea la polinoame presupune ca pentru fiecare element din polinom,
    cresterea exponentului exp++ si  coeficientul sa ia valoarea coeficientului actual / valoarea exponentului.
    Adaugarea constantei C am făcut-o direct în caseta de text din interfață.
     */
    public Polinom integrare(Polinom poli){
        Polinom poliIntegrat = new Polinom();
        for(Integer i : poli.getMap().keySet()){
            Integer exp = i; exp++;
            Double coef = poli.getMap().get(i);
            Double coef2 =coef * (1.0/exp);

            DecimalFormat form = new DecimalFormat("#.###",new DecimalFormatSymbols(Locale.US));
            String formatted = form.format(coef2);
            coef2 = Double.parseDouble(formatted);

            poliIntegrat.getMap().put(exp,coef2);
        }
        return poliIntegrat;
    }
}
