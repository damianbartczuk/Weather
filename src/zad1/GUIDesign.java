package zad1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class GUIDesign {
    private String country;
    private JFrame frame;
    private JPanel headerPanel;
    private JPanel weatherPanel;
    private JPanel currencyPanel;
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel wideSpeedLabel;
    private JLabel currencyToPLNLabel;
    private JLabel currencyFromToLabel;

    public void setTemperatureLabel(String toAddition) {
        this.temperatureLabel.setText(toAddition);
    }

    public JLabel getTemperatureLabel() {
        return temperatureLabel;
    }

    public JLabel getHumidityLabel() {
        return humidityLabel;
    }

    public void setHumidityLabel(String toAddition) {
        this.humidityLabel.setText(toAddition);
    }

    public JLabel getWideSpeedLabel() {
        return wideSpeedLabel;
    }

    public JLabel getCurrencyToPLNLabel() {
        return currencyToPLNLabel;
    }

    public JLabel getCurrencyFromTo() {
        return currencyFromToLabel;
    }

    void show() {
        country = JOptionPane.showInputDialog("Wprowadź miasto: ");
        config();
        createAndAddPanel();
        setBorderTo(country, headerPanel);
        setBorderTo("Weather", weatherPanel);
        setBorderTo("Currency in" + country, currencyPanel);
        temperatureLabel = new JLabel("Temepratura = ");
        humidityLabel = new JLabel("Wilgotnosc = ");
        wideSpeedLabel = new JLabel("prędkosc wiatru = ");
        currencyToPLNLabel = new JLabel();
        currencyFromToLabel = new JLabel();
        temperatureLabel.setHorizontalAlignment(JLabel.CENTER);
        wideSpeedLabel.setHorizontalAlignment(JLabel.CENTER);
        humidityLabel.setHorizontalAlignment(JLabel.CENTER);
        weatherPanel.add(temperatureLabel);
        weatherPanel.add(humidityLabel);
        weatherPanel.add(wideSpeedLabel);
        currencyPanel.add(currencyToPLNLabel);
        currencyPanel.add(currencyFromToLabel);
        currencyFromToLabel.setHorizontalAlignment(JLabel.CENTER);
        currencyToPLNLabel.setHorizontalAlignment(JLabel.CENTER);
    }


    private void setBorderTo(String description, JPanel panel) {
        TitledBorder titledBorder = new TitledBorder(description);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleColor(new Color(40, 21, 255));
        panel.setBorder(titledBorder);
    }

    private void config() {
        frame = new JFrame("GUI");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension(dim.width / 4, dim.height));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocation(new Point(dim.width / 3, 0));
        frame.pack();
    }

    private void createAndAddPanel() {
        headerPanel = new JPanel();
        weatherPanel = new JPanel();
        currencyPanel = new JPanel();
        currencyPanel.setLayout(new GridLayout(4,1));
        weatherPanel.setLayout(new GridLayout(3,1));
        frame.add(headerPanel);
        frame.add(weatherPanel);
        frame.add(currencyPanel);
        Button showInfo = new Button("show info about "+country);
        headerPanel.add(showInfo);
        showInfo.addActionListener((e) ->
            WebViewApp.run(country)
        );
    }
}
