package io.turbo.random;

import lombok.Getter;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Locale;

import static java.lang.System.out;

public class Main extends JFrame {
        private JPanel panelMain;
        private JTextField widthInput;
        private JTextField fovOutput;
        private JSlider focalSlider;
        private JLabel heightLabel;
        private JLabel focalLabel;
        private JLabel widthLabel;
        private JLabel fovLabel;
        private JSpinner focalInput;
        private JLabel errorLabel;
        private JLabel Label;
        private JTextField heightInput;
        private JLabel versionLabel;
        private JToolBar toolBar;
        private static String osType;
        /*
         * the current version of ftf
         *
         * @param version the version of ftf, circles are round
         * @return returns the version to display gui
         */
        @Getter private String version = "1.3.0";


    public static void main(String[] args) throws Exception {
        i().tryShowPopup();
    }

    public static Main i() {
        return new Main();
    }


        public void tryShowPopup() throws Exception {
            setTitle("Focal to FOV Converter");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setSize(365, 230);
            setLocationRelativeTo(null);
            JPanel panelMain = new JPanel();
            GroupLayout layout = new GroupLayout(panelMain);
            panelMain.setLayout(layout);
            String osName = System.getProperty("os.name", "unknown").toLowerCase(Locale.ENGLISH);
            String ver = System.getProperty("os.version");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            /* Not-needed system info about 
            *  user that prints to console
            *  just added this for fun
            */
                
            if (osName.contains("win")) {
                osType = "Windows";
                out.println("user is running on windows!");
                out.println("version: " + ver);
            } else if (osName.contains("mac")) {
                osType = "macOS";
                out.println("user is running on mac!");
                out.println("version:" + ver);
            } else if (osName.contains("nux") || osName.contains("nix")) {
                osType = "Linux/Unix";
                out.println("version:" + ver);
            }

            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            JLabel widthLabel = new JLabel("Width:");
            JLabel heightLabel = new JLabel("Height:");
            JLabel focalLabel = new JLabel("Focal:");
            JLabel fovLabel = new JLabel("FOV:");

            widthInput = new JTextField("36");
            heightInput = new JTextField("24");
            fovOutput = new JTextField();
            fovOutput.setEditable(false);

            focalInput = new JSpinner(new SpinnerNumberModel(50, 1, 300, 1));
            focalSlider = new JSlider(1, 300, 50);

            errorLabel = new JLabel(" ");
            errorLabel.setForeground(Color.RED);

            versionLabel = new JLabel("version " + version);
            Font font = Font.getFont("Monospaced");
            versionLabel.setFont(font);
            versionLabel.setHorizontalAlignment(SwingConstants.CENTER);

            layout.setHorizontalGroup(
                    layout.createParallelGroup()
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(widthLabel)
                                            .addComponent(heightLabel)
                                            .addComponent(focalLabel)
                                            .addComponent(fovLabel))
                                    .addGroup(layout.createParallelGroup()
                                            .addComponent(widthInput)
                                            .addComponent(heightInput)
                                            .addComponent(focalInput)
                                            .addComponent(focalSlider)
                                            .addComponent(fovOutput)))
                            .addComponent(versionLabel)
                            .addComponent(errorLabel)
            );

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(widthLabel)
                                    .addComponent(widthInput))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(heightLabel)
                                    .addComponent(heightInput))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(focalLabel)
                                    .addComponent(focalInput))
                            .addComponent(focalSlider)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(fovLabel)
                                    .addComponent(fovOutput))
                            .addComponent(versionLabel)
                            .addComponent(errorLabel)
            );

            setContentPane(panelMain);
            setVisible(true);
            focalInput.addChangeListener(e -> {
                focalSlider.setValue((int) focalInput.getValue());
                calculate();
                // Remove this for regular usage, it spams console
                out.println("User changed focal value!, new value:" + focalInput.getValue());
            });

            focalSlider.addChangeListener(e -> {
                focalInput.setValue(focalSlider.getValue());
                calculate();
                // Remove this for regular usage    
                out.println("User used focal slider!, new value:" + focalSlider.getValue());
            });
            setIconImage(
                    Toolkit.getDefaultToolkit().getImage(
                            Main.class.getResource("/jiroy.png")
                    )
            );
        }
        /* The calculating logic for ftf
        *  used in all event listeners, includes
        *  error text aswell
        */

        void calculate() {
            final int sensorX = 36;
            final int sensorY = 24;
            final float sensorRatio = (float) sensorX / sensorY;

            float width;
            float height;

            try {
                width = Float.parseFloat(widthInput.getText());
                height = Float.parseFloat(heightInput.getText());
            } catch (NumberFormatException e) {
                errorLabel.setText("Invalid width/height input.");
                return;
            }

            if (width <= 0 || height <= 0) {
                errorLabel.setText("Width/Height must be > 0.");
                return;
            }

            int focal = (int) focalInput.getValue();

            if (focal == 67 || width == 67 || height == 67) {
                errorLabel.setText("SIX SEVENNNNN BOIII");
                return;
            }

            float result;
            if (width / height >= sensorRatio) {
                result = (float) (2 * Math.toDegrees(
                        Math.atan((sensorX / width * height) / (2 * focal))
                ));
            } else {
                result = (float) (2 * Math.toDegrees(
                        Math.atan(sensorY / (2.0 * focal))
                ));
            }

            fovOutput.setText(String.format("%.2f", result));
            errorLabel.setText(" ");
        }
    }






