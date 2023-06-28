package tp.org.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;
import tp.org.logic.Operatie;
import tp.org.model.Polinom;
import javax.swing.*;

public class Controller implements ActionListener {
    private MainFrame frame;
    private Operatie operatie;
    public Controller(MainFrame fereastra){
        this.frame = fereastra;
        this.operatie = new Operatie();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String comanda = e.getActionCommand();
        Polinom poli1 = frame.getPoli1();
        Polinom poli2 = frame.getPoli2();

        ///daca butonul de egal este apasat atunci
        if(Objects.equals(comanda, "Egal")) {
            ///se poate ca polinoamele sa nu fi fost introduse corect
            if (poli1.getMap().size() == 0 || poli2.getMap().size() == 0) {
                frame.getTextRezultat().setText("0.0");
                JOptionPane.showMessageDialog(this.frame, "Adauga/Salveaza polinom/e!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (frame.getAdunare().isSelected()) {
                // in acest punct polinoamele sunt introduse, cautam care operatie este selectata
                frame.setAdunarePoli(operatie.adunare(poli1, poli2));
                frame.getTextRezultat().setText(frame.getAdunarePoli().toString());

            } else if (frame.getScadere().isSelected()) {
                frame.setScaderePoli(operatie.scadere(poli1, poli2));
                frame.getTextRezultat().setText(frame.getScaderePoli().toString());

            } else if (frame.getInmultire().isSelected()) {
                frame.setInmultirePoli(operatie.inmultire(poli1, poli2));
                frame.getTextRezultat().setText(frame.getInmultirePoli().toString());

            } else if (frame.getDerivare().isSelected()) {

                frame.setDerivarePoli(operatie.derivare(poli1));
                frame.getTextRezultat().setText(frame.getDerivarePoli().toString());

            } else if (frame.getIntegrare().isSelected()) {

                frame.setIntegrarePoli(operatie.integrare(poli1));
                frame.getTextRezultat().setText(frame.getIntegrarePoli().toString() + "+C");

            } else if (frame.getImpartire().isSelected()) {
                frame.setImpartirePoli(operatie.impartire(poli1, poli2));

                if (frame.getImpartirePoli().getMap().size() == 0)
                    frame.getTextRezultat().setText("IMPOSIBIL");
                else{
                    frame.getTextRezultat().setText(frame.getImpartirePoli().toString() + "\n\n" + frame.getRestPoli().toString());
                }
            }
            else{
                ///daca s-a ajuns aici cel mai probabil nicio operatie nu a fost selectata
                JOptionPane.showMessageDialog(this.frame, "Selecteaza operatie!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
