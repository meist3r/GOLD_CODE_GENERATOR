import javax.swing.*;
import java.awt.*;

public class GUI {
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
        JTextField array1TextField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel1.add(array1Label, constraints);

        constraints.gridx = 1;
        array1TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.add(array1TextField, constraints);

        // Drugi wiersz
        JLabel array2Label = new JLabel("Wielomian generujący 2 (np. 5,2):");
        JTextField array2TextField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel1.add(array2Label, constraints);

        constraints.gridx = 1;
        array2TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.add(array2TextField, constraints);

        // Trzeci wiersz
        JLabel string1Label = new JLabel("Ziarno 1 (np. 0001):");
        JTextField string1TextField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel1.add(string1Label, constraints);

        constraints.gridx = 1;
        string1TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.add(string1TextField, constraints);

        // Czwarty wiersz
        JLabel string2Label = new JLabel("Ziarno 2 (np. 0001):");
        JTextField string2TextField = new JTextField(20);

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
        JRadioButton radioButton1 = new JRadioButton("Autokorelacja 1 wielomianu");
        JRadioButton radioButton2 = new JRadioButton("Autokorelacja kodu");
        radioPanel1.add(radioButton1);
        radioPanel1.add(radioButton2);

        JPanel radioPanel2 = new JPanel();
        radioPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JRadioButton radioButton4 = new JRadioButton("Korelacja krzyżowa");
        JRadioButton radioButton3 = new JRadioButton("Autokorelacja 2 wielomianu");
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
        JButton generateButton = new JButton("Generuj wykres");
        generateButton.addActionListener(e -> generateChart());

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel1.add(generateButton, constraints);

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

        JRadioButton radioButton5 = new JRadioButton("x^5 + x^4 + x^3 + x^2 oraz x^5 + x^2");
        JRadioButton radioButton6 = new JRadioButton("x^6 + x^5 + x^2 + x oraz x^6 + x");
        JRadioButton radioButton7 = new JRadioButton("x^7 + x^3 + x^2 + x oraz x^7 + x^3");
        JRadioButton radioButton8 = new JRadioButton("x^7 + x^3 + x^1 oraz x^7 + x");
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
        JLabel string3Label = new JLabel("Ziarno 1 (np. 0001):");
        JTextField string3TextField = new JTextField(20);
        panel2.add(string3Label, constraints);
        constraints.gridx = 1;
        string3TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel2.add(string3TextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        JLabel string4Label = new JLabel("Ziarno 2 (np. 0001):");
        JTextField string4TextField = new JTextField(20);
        panel2.add(string4Label, constraints);

        constraints.gridx = 1;
        string4TextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel2.add(string4TextField, constraints);

        JLabel radioButtons3Label = new JLabel("Wybierz typ wykresu:");
        JPanel radioPanel3 = new JPanel();
        radioPanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JRadioButton radioButton9 = new JRadioButton("Autokorelacja 1 wielomianu");
        JRadioButton radioButton10 = new JRadioButton("Autokorelacja kodu");
        radioPanel3.add(radioButton9);
        radioPanel3.add(radioButton10);

        JPanel radioPanel4 = new JPanel();
        radioPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        JRadioButton radioButton12 = new JRadioButton("Korelacja krzyżowa");
        JRadioButton radioButton11 = new JRadioButton("Autokorelacja 2 wielomianu");
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
        JButton generateButton2 = new JButton("Generuj wykres");
        generateButton2.addActionListener(e -> generateChart());
        panel2.add(generateButton2, constraints);



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

    private static void generateChart() {
        // Generowanie wykresu
    }
}
