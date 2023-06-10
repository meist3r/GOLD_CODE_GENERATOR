import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.OptionalInt;

public class GUI {

    private static JTextField array1TextField;
    private static JTextField array2TextField;
    private static JTextField array3TextField;
    private static JTextField array4TextField;
    private static JLabel string1Label;
    private static JLabel string2Label;
    private static JLabel string3Label;
    private static JLabel string4Label;
    private static JLabel errorMessage1;
    private static JLabel errorMessage2;
    private static JTextField string1TextField;
    private static JTextField string2TextField;
    private static JTextField string3TextField;
    private static JTextField string4TextField;
    private static JTextField goldCodeField1;
    private static JTextField goldCodeField2;
    private static JLabel optimalInfo;
    private static JRadioButton radioButton1;
    private static JRadioButton radioButton2;
    private static JRadioButton radioButton3;
    private static JRadioButton radioButton4;
    private static JRadioButton radioButton5;
    private static JRadioButton radioButton6;
    private static JRadioButton radioButton7;
    private static JRadioButton radioButton8;
    private static JRadioButton radioButton9;
    private static JRadioButton radioButton10;
    private static JRadioButton radioButton11;
    private static JRadioButton radioButton12;
    private static JButton generateButton;
    private static JButton generateButton2;
    private static JButton generateButton3;
    private static JButton generateButton4;
    private static JButton generateButton5;
    private static JButton generateButton6;
    private static Generator generator;
    private static Validator validator;




    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Tworzenie głównego okna JFrame
        JFrame frame = new JFrame("Generator kodów Golda by Artur Grochal & Michał Wysocki");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tworzenie panelu 1
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel panelTitle = new JLabel("<html><span style='font-size:16px'>Niestandardowy generator</span></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel1.add(panelTitle, constraints);

        // Dodawanie przycisku do panelu 1
        JButton switchButton = new JButton("Przełącz na standardowy generator");
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel1.add(switchButton, constraints);

        // Pierwszy wiersz
        JLabel array1Label = new JLabel("Wielomian generujący 1 (np. 5,4,3,2):");
        array1TextField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel1.add(array1Label, constraints);

        constraints.gridx = 1;
        array1TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        array1TextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel(1);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel(1);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel(1);
            }
        });

        panel1.add(array1TextField, constraints);

        // Drugi wiersz
        JLabel array2Label = new JLabel("Wielomian generujący 2 (np. 5,2):");
        array2TextField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel1.add(array2Label, constraints);

        constraints.gridx = 1;
        array2TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        array2TextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel(2);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel(2);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel(2);
            }
        });

        panel1.add(array2TextField, constraints);

        // Trzeci wiersz
        string1Label = new JLabel("Ziarno 1 (np. 0001...):");
        string1TextField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel1.add(string1Label, constraints);

        constraints.gridx = 1;
        string1TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.add(string1TextField, constraints);

        // Czwarty wiersz
        string2Label = new JLabel("Ziarno 2 (np. 0001...):");
        string2TextField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel1.add(string2Label, constraints);

        constraints.gridx = 1;
        string2TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.add(string2TextField, constraints);

        // Piąty wiersz
        JLabel radioButtonsLabel = new JLabel("Wybierz typ wykresu:");

        JPanel radioPanel1 = new JPanel();
        radioPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButton1 = new JRadioButton("Autokorelacja 1 wielomianu");
        radioButton2 = new JRadioButton("Autokorelacja kodu");
        radioPanel1.add(radioButton1);
        radioPanel1.add(radioButton2);

        JPanel radioPanel2 = new JPanel();
        radioPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButton4 = new JRadioButton("Korelacja krzyżowa");
        radioButton3 = new JRadioButton("Autokorelacja 2 wielomianu");
        radioPanel2.add(radioButton3);
        radioPanel2.add(radioButton4);

        // Grupa dla przycisków typu radio, aby można było wybrać tylko jeden
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel1.add(radioButtonsLabel, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel1.add(radioPanel1, constraints);

        constraints.gridy = 6;
        panel1.add(radioPanel2, constraints);

        // Przycisk
        generateButton = new JButton("Generuj wykres");
        generateButton.addActionListener(e -> generateChart(1));

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel1.add(generateButton, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 8;

        generateButton3 = new JButton("Generuj kod Golda");
        generateButton3.addActionListener(e -> generateSequence(1));
        panel1.add(generateButton3, constraints);
        constraints.gridx = 1;
        generateButton4 = new JButton("Generuj kod Golda do pliku");
        generateButton4.addActionListener(e -> generateSequence(2));
        panel1.add(generateButton4, constraints);

        goldCodeField1 = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        goldCodeField1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        goldCodeField1.setBackground(Color.WHITE);
        goldCodeField1.setEditable(false);
        panel1.add(goldCodeField1, constraints);

        optimalInfo = new JLabel("Twoja para wielomianów generujących jest:");
        constraints.gridx = 0;
        constraints.gridy = 10;
        panel1.add(optimalInfo, constraints);

        errorMessage1 = new JLabel(" ");
        constraints.gridx = 0;
        constraints.gridy = 11;
        errorMessage1.setForeground(Color.RED);
        panel1.add(errorMessage1, constraints);


        // Tworzenie panelu 2
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel panel2Title = new JLabel("<html><span style='font-size:16px'>Standardowy generator</span></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel2.add(panel2Title, constraints);

        JButton backButton = new JButton("Przełącz na niestandardowy generator");
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel2.add(backButton, constraints);


        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        JLabel radioButtons2Label = new JLabel("Wybierz predefiniowaną parę wielomianów generujących:");
        panel2.add(radioButtons2Label,constraints);

        radioButton5 = new JRadioButton("x^5 + x^4 + x^3 + x^2 oraz x^5 + x^2");
        radioButton6 = new JRadioButton("x^6 + x^5 + x^2 + x oraz x^6 + x");
        radioButton7 = new JRadioButton("x^7 + x^3 + x^2 + x oraz x^7 + x^3");
        radioButton8 = new JRadioButton("x^7 + x^3 + x^1 oraz x^7 + x");
        radioButton5.addItemListener(e -> {
            if (radioButton5.isSelected()) {
                updateLabel(3);
            }
        });

        radioButton6.addItemListener(e -> {
            if (radioButton6.isSelected()) {
                updateLabel(4);
            }
        });

        radioButton7.addItemListener(e -> {
            if (radioButton7.isSelected()) {
                updateLabel(5);
            }
        });

        radioButton8.addItemListener(e -> {
            if (radioButton8.isSelected()) {
                updateLabel(6);
            }
        });
        constraints.gridy = 2;
        panel2.add(radioButton5,constraints);
        constraints.gridy = 3;
        panel2.add(radioButton6,constraints);
        constraints.gridy = 4;
        panel2.add(radioButton7,constraints);
        constraints.gridy = 5;
        panel2.add(radioButton8,constraints);
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(radioButton5);
        buttonGroup2.add(radioButton6);
        buttonGroup2.add(radioButton7);
        buttonGroup2.add(radioButton8);

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 6;
        string3Label = new JLabel("Ziarno 1 (np. 0001...):");
        string3TextField = new JTextField(20);
        panel2.add(string3Label, constraints);
        constraints.gridx = 1;
        string3TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel2.add(string3TextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        string4Label = new JLabel("Ziarno 2 (np. 0001...):");
        string4TextField = new JTextField(20);
        panel2.add(string4Label, constraints);

        constraints.gridx = 1;
        string4TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel2.add(string4TextField, constraints);

        JLabel radioButtons3Label = new JLabel("Wybierz typ wykresu:");
        JPanel radioPanel3 = new JPanel();
        radioPanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButton9 = new JRadioButton("Autokorelacja 1 wielomianu");
        radioButton10 = new JRadioButton("Autokorelacja kodu");
        radioPanel3.add(radioButton9);
        radioPanel3.add(radioButton10);

        JPanel radioPanel4 = new JPanel();
        radioPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButton12 = new JRadioButton("Korelacja krzyżowa");
        radioButton11 = new JRadioButton("Autokorelacja 2 wielomianu");
        radioPanel4.add(radioButton11);
        radioPanel4.add(radioButton12);
        ButtonGroup buttonGroup3 = new ButtonGroup();
        buttonGroup3.add(radioButton9);
        buttonGroup3.add(radioButton10);
        buttonGroup3.add(radioButton11);
        buttonGroup3.add(radioButton12);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel2.add(radioButtons3Label, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel2.add(radioPanel3, constraints);

        constraints.gridy = 9;
        panel2.add(radioPanel4, constraints);


        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        generateButton2 = new JButton("Generuj wykres");
        generateButton2.addActionListener(e -> generateChart(2));
        panel2.add(generateButton2, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 11;

        generateButton5 = new JButton("Generuj kod Golda");
        generateButton5.addActionListener(e -> generateSequence(3));
        panel2.add(generateButton5, constraints);
        constraints.gridx = 1;
        generateButton6 = new JButton("Generuj kod Golda do pliku");
        generateButton6.addActionListener(e -> generateSequence(4));
        panel2.add(generateButton6, constraints);

        goldCodeField2 = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 12;
        constraints.gridwidth = 2;
        goldCodeField2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        goldCodeField2.setBackground(Color.WHITE);
        goldCodeField2.setEditable(false);
        panel2.add(goldCodeField2, constraints);

        errorMessage2 = new JLabel(" ");
        constraints.gridx = 0;
        constraints.gridy = 13;
        errorMessage2.setForeground(Color.RED);
        panel2.add(errorMessage2, constraints);



        // Ustawienie początkowego panelu
        frame.add(panel1);

        // Dodawanie akcji dla przycisku przełączania
        switchButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); // Usunięcie wszystkich komponentów z okna
            frame.add(panel2); // Dodanie panelu 2
            frame.pack();
            frame.revalidate();
            frame.repaint();
        });

        // Dodawanie akcji dla przycisku powrotu
        backButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); // Usunięcie wszystkich komponentów z okna
            frame.add(panel1); // Dodanie panelu 1
            frame.pack();
            frame.revalidate();
            frame.repaint();
        });

        // Wyśrodkowanie i wyświetlenie okna
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void generateChart(int panel) {
        if(panel==1){
            String array1String = array1TextField.getText();
            String array2String = array2TextField.getText();
            int[] seed1 = Utils.extractSeed(string1TextField.getText());
            int[] seed2 = Utils.extractSeed(string2TextField.getText());

            int[] array1 = Utils.extractMSeq(array1String);
            int[] array2 = Utils.extractMSeq(array2String);

            int aMax1 = Utils.maxIntArr(array1);
            int aMax2 = Utils.maxIntArr(array2);

            int seedLen = Math.max(aMax1,aMax2);

            if(aMax1==0){
                errorMessage1.setText("Uzupełnij wielomian 1");
                return;
            }

            if(aMax2==0){
                errorMessage1.setText("Uzupełnij wielomian 2");
                return;
            }

            if(seed1.length!=seedLen){
                errorMessage1.setText("Ziarno 1 ma nieprawidłową długość");
                return;
            }

            if(seed2.length!=seedLen){
                errorMessage1.setText("Ziarno 2 ma nieprawidłową długość");
                return;
            }


            generator = new Generator(array1, array2, seed1, seed2);
            validator = new Validator(generator);

            if (radioButton1.isSelected()) {
                int[] mSeq1AutoCorArr = validator.getMSequencesAutoCorrelation(1);
                LineChart.generateLineChart(Utils.intToDouble(mSeq1AutoCorArr), "Autokorelacja pierwszego wielomianu");
            } else if (radioButton2.isSelected()) {
                int[] AutoCorArr = validator.getAutoCorrelation();
                LineChart.generateLineChart(Utils.intToDouble(AutoCorArr), "Autokorelacja");
            } else if (radioButton3.isSelected()) {
                int[] mSeq2AutoCorArr = validator.getMSequencesAutoCorrelation(2);
                LineChart.generateLineChart(Utils.intToDouble(mSeq2AutoCorArr), "Autokorelacja drugiego wielomianu");
            } else if (radioButton4.isSelected()) {
                int[] mSeqCorArr = validator.getMSequencesCorrelation();
                LineChart.generateLineChart(Utils.intToDouble(mSeqCorArr), "Korelacja krzyżowa");
            } else {
                errorMessage1.setText("Wybierz typ wykresu");
            }


        }else if(panel==2){
            int[] pair1 = {};
            int[] pair2 = {};
            int radioSwitch;
            int seedLen = 0;


            if (radioButton5.isSelected()) {
                radioSwitch = 1;
            } else if (radioButton6.isSelected()) {
                radioSwitch = 2;
            } else if (radioButton7.isSelected()) {
                radioSwitch = 3;
            } else if (radioButton8.isSelected()) {
                radioSwitch = 4;
            } else {
                errorMessage2.setText("Wybierz parę wielomianów");
                return;
            }

            switch (radioSwitch) {
                case 1 -> {
                    pair1 = new int[]{2, 3, 4, 5};
                    pair2 = new int[]{2, 5};
                    seedLen = 5;
                }
                case 2 -> {
                    pair1 = new int[]{1, 2, 5, 6};
                    pair2 = new int[]{1, 6};
                    seedLen = 6;
                }
                case 3 -> {
                    pair1 = new int[]{1, 2, 3, 7};
                    pair2 = new int[]{3, 7};
                    seedLen = 7;
                }
                case 4 -> {
                    pair1 = new int[]{1, 3, 7};
                    pair2 = new int[]{1, 7};
                    seedLen = 7;
                }
            }
            int[] seed1 = Utils.extractSeed(string3TextField.getText());
            int[] seed2 = Utils.extractSeed(string4TextField.getText());

            if(seed1.length!=seedLen){
                errorMessage2.setText("Ziarno 1 ma nieprawidłową długość");
                return;
            }

            if(seed2.length!=seedLen){
                errorMessage2.setText("Ziarno 2 ma nieprawidłową długość");
                return;
            }

            generator = new Generator(pair1, pair2, seed1, seed2);
            validator = new Validator(generator);

            if (radioButton9.isSelected()) {
                int[] mSeq1AutoCorArr = validator.getMSequencesAutoCorrelation(1);
                LineChart.generateLineChart(Utils.intToDouble(mSeq1AutoCorArr), "Autokorelacja pierwszego wielomianu");
            } else if (radioButton10.isSelected()) {
                int[] AutoCorArr = validator.getAutoCorrelation();
                LineChart.generateLineChart(Utils.intToDouble(AutoCorArr), "Autokorelacja");
            } else if (radioButton11.isSelected()) {
                int[] mSeq2AutoCorArr = validator.getMSequencesAutoCorrelation(2);
                LineChart.generateLineChart(Utils.intToDouble(mSeq2AutoCorArr), "Autokorelacja drugiego wielomianu");
            } else if (radioButton12.isSelected()) {
                int[] mSeqCorArr = validator.getMSequencesCorrelation();
                LineChart.generateLineChart(Utils.intToDouble(mSeqCorArr), "Korelacja krzyżowa");
            } else {
                errorMessage1.setText("Wybierz typ wykresu");
            }
        }

    }

    private static void generateSequence(int i){
        int[] seed1 = {};
        int[] seed2 = {};
        int[] pair1 = {};
        int[] pair2 = {};
        int seedLen = 0;
        switch (i) {
            case 1,2 -> {
                String array1String = array1TextField.getText();
                String array2String = array2TextField.getText();
                seed1 = Utils.extractSeed(string1TextField.getText());
                seed2 = Utils.extractSeed(string2TextField.getText());

                pair1 = Utils.extractMSeq(array1String);
                pair2 = Utils.extractMSeq(array2String);

                int aMax1 = Utils.maxIntArr(pair1);
                int aMax2 = Utils.maxIntArr(pair2);

               seedLen = Math.max(aMax1,aMax2);

                if(seed1.length!=seedLen){
                    errorMessage1.setText("Ziarno 1 ma nieprawidłową długość");
                    return;
                }

                if(seed2.length!=seedLen){
                    errorMessage1.setText("Ziarno 2 ma nieprawidłową długość");
                    return;
                }

            }
            case 3,4 -> {
                int radioSwitch;
                if (radioButton5.isSelected()) {
                    radioSwitch = 1;
                } else if (radioButton6.isSelected()) {
                    radioSwitch = 2;
                } else if (radioButton7.isSelected()) {
                    radioSwitch = 3;
                } else if (radioButton8.isSelected()) {
                    radioSwitch = 4;
                } else {
                    errorMessage2.setText("Wybierz parę wielomianów");
                    return;
                }

                switch (radioSwitch) {
                    case 1 -> {
                        pair1 = new int[]{2, 3, 4, 5};
                        pair2 = new int[]{2, 5};
                        seedLen = 5;
                    }
                    case 2 -> {
                        pair1 = new int[]{1, 2, 5, 6};
                        pair2 = new int[]{1, 6};
                        seedLen = 6;
                    }
                    case 3 -> {
                        pair1 = new int[]{1, 2, 3, 7};
                        pair2 = new int[]{3, 7};
                        seedLen = 7;
                    }
                    case 4 -> {
                        pair1 = new int[]{1, 3, 7};
                        pair2 = new int[]{1, 7};
                        seedLen = 7;
                    }
                }
                seed1 = Utils.extractSeed(string3TextField.getText());
                seed2 = Utils.extractSeed(string4TextField.getText());

                if(seed1.length!=seedLen){
                    errorMessage2.setText("Ziarno 1 ma nieprawidłową długość");
                    return;
                }

                if(seed2.length!=seedLen){
                    errorMessage2.setText("Ziarno 2 ma nieprawidłową długość");
                    return;
                }
            }
        }

        generator = new Generator(pair1, pair2, seed1, seed2);
        validator = new Validator(generator);

        String goldCode = Utils.intArrayToString(generator.generateFullGoldCode());


        switch (i) {
            case 1 -> {
                goldCodeField1.setText(goldCode);
            }
            case 2,4 -> {
                StringBuilder sb = new StringBuilder();
                sb.append("Kod Golda:\n");
                sb.append(goldCode);
                int success = Utils.saveStringToFile("GoldCode.txt", sb.toString());
                if(success!=1){
                    errorMessage2.setText("Nie udało zapisać się kodu do pliku");
                }
            }
            case 3 -> {
                goldCodeField2.setText(goldCode);
            }
        }

    }

    private static void updateLabel(int i) {

        if(i==1||i==2){
            String array1String = array1TextField.getText();
            String array2String = array2TextField.getText();
            int[] array1 = Utils.extractMSeq(array1String);
            int[] array2 = Utils.extractMSeq(array2String);
            int aMax1 = Utils.maxIntArr(array1);
            int aMax2 = Utils.maxIntArr(array2);

            int seedLen = Math.max(aMax1,aMax2);

            if(seedLen!=0){
                string1Label.setText("Ziarno 1 o długości " + seedLen + " (np. 0001...):");
                string2Label.setText("Ziarno 2 o długości " + seedLen + " (np. 0001...):");
            } else {
                string1Label.setText("Ziarno 1 (np. 0001...):");
                string2Label.setText("Ziarno 2 (np. 0001...):");
            }

            if(aMax1 == 0 || aMax2 == 0){
                optimalInfo.setText("Twoja para wielomianów generujących jest:");
                return;
            }

            int[] seed1 = Utils.generateSeed(seedLen);
            int[] seed2 = Utils.generateSeed(seedLen);

            Generator temporaryGenerator = new Generator(array1, array2, seed1, seed2);
            Validator temporaryValidator = new Validator(temporaryGenerator);

            boolean isOptimal = temporaryValidator.isPreferredSequences();

            if(isOptimal){
                optimalInfo.setText("Twoja para wielomianów generujących jest: OPTYMALNA");
            } else {
                optimalInfo.setText("Twoja para wielomianów generujących jest: NIEOPTYMALNA");
            }



        } else if(i==3||i==4||i==5||i==6){
            int seedLen = 0;
            switch(i) {
                case 3 -> seedLen = 5;
                case 4 -> seedLen = 6;
                case 5, 6 -> seedLen = 7;
            }

            if(seedLen!=0){
                string3Label.setText("Ziarno 1 o długości " + seedLen + " (np. 0001...):");
                string4Label.setText("Ziarno 2 o długości " + seedLen + " (np. 0001...):");
            } else {
                string3Label.setText("Ziarno 1 (np. 0001...):");
                string4Label.setText("Ziarno 2 (np. 0001...):");
            }


        }

    }
}
