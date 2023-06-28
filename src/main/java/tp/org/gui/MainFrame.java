package tp.org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tp.org.model.Polinom;

public class MainFrame extends JFrame {

    private boolean isTextPoli1Focused,isTextPoli2Focused= false;
    private Polinom poli1,poli2;
    private Polinom adunarePoli,scaderePoli,inmultirePoli,derivarePoli,integrarePoli, impartirePoli,restPoli;
    private JTextPane textPoli1,textPoli2,textRezultat;
    private JPanel panel_1, panel_2,panel_3;
    private JLabel lblAlDoileaPolinom,lblRezultatul,lblNewLabel;
    private JRadioButton adunare,scadere,inmultire,impartire,derivare,integrare;
    private ButtonGroup buttonGroup;
    private Controller controller;
    public MainFrame() {
        controller = new Controller(this);
        //polinoame
        poli1 = new Polinom();
        poli2 = new Polinom();
        adunarePoli = new Polinom();
        impartirePoli = new Polinom();
        restPoli = new Polinom();
        scaderePoli = new Polinom();
        inmultirePoli = new Polinom();
        derivarePoli = new Polinom();
        integrarePoli = new Polinom();

        this.initializareFrame();
    }
    public void initializareFrame(){

        this.setTitle("CALCULATOR POLINOMIAL");
        this.setResizable(false);
        this.setBackground(new Color(0, 0, 0));
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0, 94, 94));
        this.getContentPane().setLayout(null);
        this.setLocation(350, 10);
        this.setSize(600,650);
        butonSave1();butonSave2(); butonDelete();
        createPanel1();createPanel2();createPanel3();
        labelAll();
        textPoli1();textPoli2();textPoliRezultat();
        this.setVisible(true);
    }
    public void labelAll(){
        this.lblNewLabel = new JLabel("PRIMUL POLINOM");
        this.lblNewLabel.setForeground(new Color(0, 0, 0));
        this.lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 15));
        this.lblNewLabel.setBackground(new Color(0, 66, 66));
        this.lblNewLabel.setBounds(22, 36, 147, 34);
        this.getContentPane().add(lblNewLabel);

        this.lblAlDoileaPolinom = new JLabel("AL DOILEA POLINOM");
        this.lblAlDoileaPolinom.setForeground(Color.BLACK);
        this.lblAlDoileaPolinom.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 15));
        this.lblAlDoileaPolinom.setBackground(new Color(0, 66, 66));
        this.lblAlDoileaPolinom.setBounds(22, 104, 155, 34);
        this.getContentPane().add(lblAlDoileaPolinom);

        this.lblRezultatul = new JLabel("REZULTATUL:");
        this.lblRezultatul.setForeground(Color.BLACK);
        this.lblRezultatul.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 15));
        this.lblRezultatul.setBackground(new Color(0, 66, 66));
        this.lblRezultatul.setBounds(22, 172, 112, 28);
        this.getContentPane().add(lblRezultatul);
    }
    public void butonInapoi(){
        JButton btnInapoi = new JButton("INAPOI");
        btnInapoi.setFont(new Font("Yu Gothic UI", Font.ITALIC, 17));
        btnInapoi.setForeground(Color.BLACK);
        btnInapoi.setBackground(new Color(0, 66, 66));
        this.panel_2.add(btnInapoi);
        btnInapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isTextPoli1Focused) {
                    String currentText1 = textPoli1.getText();
                    int x = currentText1.length();
                    if(x>0) {
                        --x;
                        String nou = (String) currentText1.subSequence(0, x);
                        textPoli1.setText(nou);
                    }
                }
                else if(isTextPoli2Focused){
                    String currentText2 = textPoli2.getText();
                    int x = currentText2.length();
                    if(x>0) {
                        --x;
                        String nou = (String) currentText2.subSequence(0, x);
                        textPoli2.setText(nou);
                    }
                }
            }
        });
    }
    public void butonSterge(){
        JButton btnSterge = new JButton("STERGE");
        btnSterge.setFont(new Font("Yu Gothic UI", Font.ITALIC, 17));
        btnSterge.setForeground(Color.BLACK);
        btnSterge.setBackground(new Color(0, 66, 66));
        this.panel_2.add(btnSterge);
        btnSterge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isTextPoli1Focused) {
                    textPoli1.setText("");
                }
                else if(isTextPoli2Focused){
                    textPoli2.setText("");
                }
            }
        });
    }
    public void butonMinus(){
        JButton btnMinus = new JButton("-");
        btnMinus.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btnMinus.setForeground(Color.BLACK);
        btnMinus.setBackground(new Color(0, 66, 66));
        this.panel_2.add(btnMinus);
        btnMinus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "-");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "-");
                }
            }
        });
    }
    public void butonPunct(){
        JButton btnPunct = new JButton(".");
        btnPunct.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btnPunct.setForeground(Color.BLACK);
        btnPunct.setBackground(new Color(0, 66, 66));
        this.panel_2.add(btnPunct);
        btnPunct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + ".");
                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + ".");
                }
            }
        });
    }
    public void butonX(){
        JButton btnX = new JButton("x");
        btnX.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btnX.setForeground(Color.BLACK);
        btnX.setBackground(new Color(0, 66, 66));
        this.panel_2.add(btnX);
        btnX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "x");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "x");
                }
            }
        });
    }
    public void butonDelete(){
        JButton btnDelete = new JButton("DELETE");
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setFont(new Font("Yu Gothic UI", Font.ITALIC, 20));
        btnDelete.setBackground(new Color(0, 66, 66));
        btnDelete.setBounds(428, 172, 99, 66);
        this.getContentPane().add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPoli1.setText("");
                textPoli2.setText("");
                textRezultat.setText("");
                poli1.getMap().clear();
                poli2.getMap().clear();
                isTextPoli1Focused = true;
            }
        });
    }
    public void butonPutere(){
        JButton btnPutere = new JButton("^");
        btnPutere.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btnPutere.setForeground(Color.BLACK);
        btnPutere.setBackground(new Color(0, 66, 66));
        this.panel_2.add(btnPutere);
        btnPutere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "^");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "^");
                }
            }
        });
    }
    public void textPoliRezultat(){
        this.textRezultat = new JTextPane();
        this.textRezultat.setBackground(new Color(97, 175, 175));
        this.textRezultat.setBounds(187, 173, 205, 66);
        getContentPane().add(textRezultat);
        {
            JLabel lblRestul = new JLabel("RESTUL IMPARTIRII:");
            lblRestul.setForeground(Color.BLACK);
            lblRestul.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 15));
            lblRestul.setBackground(new Color(0, 66, 66));
            lblRestul.setBounds(22, 211, 147, 28);
            getContentPane().add(lblRestul);
        }
    }
    public void textPoli2(){
        this.textPoli2 = new JTextPane();
        this.textPoli2.setEditable(false);
        this.textPoli2.setBackground(new Color(97, 175, 175));
        this.textPoli2.setBounds(187, 104, 205, 40);
        this.getContentPane().add(textPoli2);
        this.textPoli2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isTextPoli1Focused = false;
                isTextPoli2Focused = true;
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }
    public void textPoli1(){
        this.textPoli1 = new JTextPane();
        this.textPoli1.setEditable(false);
        this.textPoli1.setBackground(new Color(97, 175, 175));
        this.textPoli1.setBounds(187, 31, 205, 40);
        this.getContentPane().add(textPoli1);
        this. textPoli1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isTextPoli1Focused = true;
                isTextPoli2Focused = false;
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }
    public void butonSave1(){
        JButton btnSave1 = new JButton("SAVE");
        btnSave1.setForeground(Color.BLACK);
        btnSave1.setFont(new Font("Yu Gothic UI", Font.ITALIC, 20));
        btnSave1.setBackground(new Color(0, 66, 66));
        btnSave1.setBounds(428, 31, 94, 40);
        this.getContentPane().add(btnSave1);
        btnSave1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                poli1.getMap().clear();
                poli1.adaugaPolinomLaHashMap(textPoli1.getText());
                poli1.getStringMap();
                if(poli1.getMap().size()==0)
                    textPoli1.setText("");
                else {
                    isTextPoli1Focused = false;
                    isTextPoli2Focused = true;
                }
            }
        });
    }
    public void butonSave2(){
        JButton btnSave2 = new JButton("SAVE");
        btnSave2.setForeground(Color.BLACK);
        btnSave2.setFont(new Font("Yu Gothic UI", Font.ITALIC, 20));
        btnSave2.setBackground(new Color(0, 66, 66));
        btnSave2.setBounds(428, 104, 94, 40);
        this.getContentPane().add(btnSave2);
        btnSave2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                poli2.getMap().clear();
                poli2.adaugaPolinomLaHashMap(textPoli2.getText());
                poli2.getStringMap();
                if(poli2.getMap().size()==0)
                    textPoli2.setText("");
                else {
                    isTextPoli1Focused = false;
                    isTextPoli2Focused = false;
                }
            }
        });
    }
    public void butonPlus(){
        JButton btnPlus = new JButton("+");
        btnPlus.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btnPlus.setForeground(Color.BLACK);
        btnPlus.setBackground(new Color(0, 66, 66));
        this.panel_2.add(btnPlus);
        btnPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "+");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "+");
                }
            }
        });
    }
    public Polinom getRestPoli() {
        return restPoli;
    }
    public Polinom getPoli1() {
        return poli1;
    }
    public Polinom getPoli2() {
        return poli2;
    }
    public JRadioButton getAdunare() {
        return adunare;
    }
    public JRadioButton getScadere() {
        return scadere;
    }
    public JRadioButton getInmultire() {
        return inmultire;
    }
    public JRadioButton getImpartire() {
        return impartire;
    }
    public JRadioButton getDerivare() {
        return derivare;
    }
    public Polinom getAdunarePoli() {
        return adunarePoli;
    }
    public void setAdunarePoli(Polinom adunarePoli) {
        this.adunarePoli = adunarePoli;
    }
    public Polinom getScaderePoli() {
        return scaderePoli;
    }
    public void setScaderePoli(Polinom scaderePoli) {
        this.scaderePoli = scaderePoli;
    }
    public Polinom getInmultirePoli() {
        return inmultirePoli;
    }
    public void setInmultirePoli(Polinom inmultirePoli) {
        this.inmultirePoli = inmultirePoli;
    }
    public Polinom getDerivarePoli() {
        return derivarePoli;
    }
    public void setDerivarePoli(Polinom derivarePoli) {
        this.derivarePoli = derivarePoli;
    }
    public Polinom getIntegrarePoli() {
        return integrarePoli;
    }
    public void setIntegrarePoli(Polinom integrarePoli) {
        this.integrarePoli = integrarePoli;
    }
    public Polinom getImpartirePoli() {
        return impartirePoli;
    }
    public void setImpartirePoli(Polinom[] impartire) {
        this.impartirePoli = impartire[0];
        this.restPoli = impartire[1];
    }
    public JTextPane getTextRezultat() {
        return textRezultat;
    }
    public JRadioButton getIntegrare() {
        return integrare;
    }
    public void createPanel3(){
        this.panel_3 = new JPanel();
        panel_3.setBackground(new Color(0, 94, 94));
        this.panel_3 .setBounds(61, 266, 483, 75);
        this.panel_3 .setLayout(new GridLayout(0, 3, 0, 0));

        adunare = new JRadioButton("Adunare");
        adunare.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 17));
        adunare.setBackground(new Color(0, 94, 94));
        adunare.setForeground(new Color(0, 0, 0));
        panel_3.add(adunare);

        scadere = new JRadioButton("Scadere");
        scadere.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 17));
        scadere.setBackground(new Color(0, 94, 94));
        scadere.setForeground(new Color(0, 0, 0));
        panel_3.add(scadere);

        inmultire = new JRadioButton("Inmultire");
        inmultire.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 17));
        inmultire.setBackground(new Color(0, 94, 94));
        inmultire.setForeground(new Color(0, 0, 0));
        panel_3.add(inmultire);

        derivare = new JRadioButton("Derivare");
        derivare.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 17));
        derivare.setForeground(new Color(0, 0, 0));
        derivare.setBackground(new Color(0, 94, 94));
        panel_3.add(derivare);

        integrare = new JRadioButton("Integrare");
        integrare.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 17));
        integrare.setForeground(new Color(0, 0, 0));
        integrare.setBackground(new Color(0, 94, 94));
        panel_3.add(integrare);

        impartire = new JRadioButton("Impartire");
        impartire.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 17));
        impartire.setForeground(new Color(0, 0, 0));
        impartire.setBackground(new Color(0, 94, 94));
        panel_3.add(impartire);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(adunare);buttonGroup.add(scadere);buttonGroup.add(inmultire);
        buttonGroup.add(derivare);buttonGroup.add(integrare);buttonGroup.add(impartire);

        getContentPane().add(this.panel_3 );
    }
    public void createPanel2(){
        this.panel_2 = new JPanel();
        this.panel_2.setBounds(296, 354, 280, 248);
        this.panel_2.setLayout(new GridLayout(0, 3, 0, 0));

        buton0();butonPlus();butonMinus();
        butonX();butonPunct();butonPutere();
        butonSterge();butonInapoi();butonEgal();

        this.getContentPane().add(this.panel_2);
    }
    public void createPanel1(){
        this.panel_1 = new JPanel();
        this.panel_1.setBounds(10, 354, 280, 248);
        this.panel_1.setLayout(new GridLayout(0, 3, 0, 0));

        buton1();buton2();buton3();
        buton4();buton5();buton6();
        buton7();buton8();buton9();

        this.getContentPane().add(this.panel_1);
    }
    public void butonEgal(){
        JButton btnEgal = new JButton("EGAL");
        btnEgal.setForeground(Color.BLACK);
        btnEgal.setFont(new Font("Yu Gothic UI", Font.ITALIC, 17));
        btnEgal.setBackground(new Color(0, 66, 66));
        this.panel_2.add(btnEgal);
        btnEgal.setActionCommand("Egal");
        btnEgal.addActionListener(this.controller);
    }
    public void buton0(){
        JButton btn0 = new JButton("0");
        btn0.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn0.setForeground(Color.BLACK);
        btn0.setBackground(new Color(0, 66, 66));
        btn0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "0");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "0");
                }
            }
        });
        this.panel_2.add(btn0);
    }
    public void buton1(){

        JButton btn1 = new JButton("1");
        btn1.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn1.setForeground(new Color(0, 0, 0));
        btn1.setBackground(new Color(0, 66, 66));
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "1");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "1");
                }
            }
        });
        this.panel_1.add(btn1);
    }
    public void buton2(){
        JButton btn2 = new JButton("2");
        btn2.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn2.setForeground(new Color(0, 0, 0));
        btn2.setBackground(new Color(0, 66, 66));
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "2");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "2");
                }
            }
        });
        this.panel_1.add(btn2);
    }
    public void buton3(){
        JButton btn3 = new JButton("3");
        btn3.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn3.setForeground(new Color(0, 0, 0));
        btn3.setBackground(new Color(0, 66, 66));
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "3");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "3");
                }
            }
        });
        this.panel_1.add(btn3);
    }
    public void buton4(){

        JButton btn4 = new JButton("4");
        btn4.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn4.setForeground(new Color(0, 0, 0));
        btn4.setBackground(new Color(0, 66, 66));
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "4");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "4");
                }
            }
        });
        this.panel_1.add(btn4);
    }
    public void buton5(){
        JButton btn5 = new JButton("5");
        btn5.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn5.setForeground(new Color(0, 0, 0));
        btn5.setBackground(new Color(0, 66, 66));
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "5");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "5");
                }
            }
        });
        this.panel_1.add(btn5);
    }
    public void buton6(){
        JButton btn6 = new JButton("6");
        btn6.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn6.setForeground(new Color(0, 0, 0));
        btn6.setBackground(new Color(0, 66, 66));
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "6");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "6");
                }
            }
        });
        this.panel_1.add(btn6);
    }
    public void buton7(){
        JButton btn7 = new JButton("7");
        btn7.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn7.setForeground(new Color(0, 0, 0));
        btn7.setBackground(new Color(0, 66, 66));
        btn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "7");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "7");
                }
            }
        });
        this.panel_1.add(btn7);
    }
    public void buton8(){
        JButton btn8 = new JButton("8");
        btn8.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn8.setForeground(new Color(0, 0, 0));
        btn8.setBackground(new Color(0, 66, 66));
        btn8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "8");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "8");
                }
            }
        });
        this.panel_1.add(btn8);
    }
    public void buton9(){
        JButton btn9 = new JButton("9");
        btn9.setFont(new Font("Yu Gothic UI", Font.ITALIC, 30));
        btn9.setForeground(new Color(0, 0, 0));
        btn9.setBackground(new Color(0, 66, 66));
        btn9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTextPoli1Focused){

                    String currentText = textPoli1.getText();
                    textPoli1.setText(currentText + "9");

                }
                else
                if (isTextPoli2Focused) {

                    String currentText = textPoli2.getText();
                    textPoli2.setText(currentText + "9");
                }
            }
        });
        this.panel_1.add(btn9);
    }
}
