package tp.org.logic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tp.org.model.Polinom;
import static org.junit.Assert.assertEquals;

public class OperatiiParamTest{
    //parametrizam testele
    //functia asteapta 3 string-uri declarate anterior in sursa,
    // construim polinoamele prin parsarea string-urilor
    ///apelam pentru fiecare polinom rezultat din fiecare operatie toString-ul
    ///verificand astfel ca rezultatul este asemenea
    @ParameterizedTest
    @CsvSource({ "2x+1, 3x+2, 5.0x+3.0",
                 "3x^2+5x+2,3x,3.0x^2+8.0x+2.0",
                 "7x^2+3x+2,3x^2-3,10.0x^2+3.0x-1.0",
                 "2x-4,3x,5.0x-4.0",
                 "3x^3+5,4x^2+4,3.0x^3+4.0x^2+9.0"
    })
    public void testAdunare(String poli1,String poli2,String rezultat){

        Operatie operatie = new Operatie();
        Polinom polinom1 = new Polinom();
        polinom1.adaugaPolinomLaHashMap(poli1);
        Polinom polinom2 = new Polinom();
        polinom2.adaugaPolinomLaHashMap(poli2);
        assertEquals(rezultat, (operatie.adunare(polinom1, polinom2).toString()));
    }

    @ParameterizedTest
    @CsvSource({"2x+1, 3x+2, -1.0x-1.0",
                "3x^2+5x+2,3x,3.0x^2+2.0x+2.0",
                "7x^2+3x+2,3x^2-3,4.0x^2+3.0x+5.0",
                "2x-4,3x,-1.0x-4.0"})
    public void testScadere(String poli1,String poli2,String rezultat){
        Operatie operatie = new Operatie();
        Polinom polinom1 = new Polinom();
        polinom1.adaugaPolinomLaHashMap(poli1);
        Polinom polinom2 = new Polinom();
        polinom2.adaugaPolinomLaHashMap(poli2);
        assertEquals(rezultat, (operatie.scadere(polinom1, polinom2).toString()));
    }

    @ParameterizedTest
    @CsvSource({ "2x+1, 3x+2, 6.0x^2+7.0x+2.0",
            "3x^2+5x+2,3x,9.0x^3+15.0x^2+6.0x",
            "7x^2+3x+2,3x^2-3,21.0x^4+9.0x^3-15.0x^2-9.0x-6.0",
            "2x-4,3x,6.0x^2-12.0x"})
    public void testInmultire(String poli1,String poli2,String rezultat) {
        Operatie operatie = new Operatie();
        Polinom polinom1 = new Polinom();
        polinom1.adaugaPolinomLaHashMap(poli1);
        Polinom polinom2 = new Polinom();
        polinom2.adaugaPolinomLaHashMap(poli2);
        assertEquals(rezultat, (operatie.inmultire(polinom1, polinom2).toString()));
    }

    @ParameterizedTest
    @CsvSource({"x^2-2x+1,x-1,1.0x-1.0,0.0",
                "x^2+2x+1,x+1,1.0x+1.0,0.0",
                "x^2+x,0,0.0,0.0",
                "2x^4+x^2+x-3,x^2-4x,2.0x^2+8.0x+33.0,133.0x-3.0",
                "x^3+3x^2-x-3,x-2,x^2+5.0x+9.0,15.0"})
    public void testImpartire(String poli1,String poli2,String rezultatImp,String rezultatRest) {
        Operatie operatie = new Operatie();
        Polinom polinom1 = new Polinom();
        polinom1.adaugaPolinomLaHashMap(poli1);
        Polinom polinom2 = new Polinom();
        polinom2.adaugaPolinomLaHashMap(poli2);
        Polinom[] poli =  new Polinom[2];
        poli = operatie.impartire(polinom1,polinom2);
        assertEquals(rezultatImp,poli[0].toString());
        assertEquals(rezultatRest,poli[1].toString());
    }

    @ParameterizedTest
    @CsvSource({"2x+1, 2.0",
                "3x^2+5x+2,6.0x+5.0",
                "7x^2+3x+2,14.0x+3.0",
                "2x-4,2.0",
                "2,0.0", "3x^9+56,27.0x^8"})
    public void testDerivare(String poli1,String rezultat) {
        Operatie operatie = new Operatie();
        Polinom polinom1 = new Polinom();
        polinom1.adaugaPolinomLaHashMap(poli1);
        assertEquals(rezultat, (operatie.derivare(polinom1).toString()));
    }

    @ParameterizedTest
    @CsvSource({ "2x+1, x^2+1.0x",
                 "3x^2+5x+2,x^3+2.5x^2+2.0x",
                 "7x^2+3x+2,2.333x^3+1.5x^2+2.0x",
                 "2x-4,x^2-4.0x",
                 "2,2.0x",
                 "2x,x^2"})
    public void testIntegrare(String poli1,String rezultat) {
        Operatie operatie = new Operatie();
        Polinom polinom1 = new Polinom();
        polinom1.adaugaPolinomLaHashMap(poli1);
        assertEquals(rezultat, (operatie.integrare(polinom1).toString()));
    }

}