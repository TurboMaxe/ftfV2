package io.turbo.random;

import lombok.Getter;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        private JLabel mode;
        /* Change fun mode to false if you do not want to
        *  display error when height, focal or width are 67
        */
        private boolean fun = true;
       
        /*
         * the current version of ftf
         *
         * @param version the version of ftf, circles are round
         * @return returns the version to display gui
         */
        @Getter private String version = "1.3.0";
        @Getter private boolean debug = false;


    public static void main(String[] args) throws Exception {
        i().tryShowPopup(UIManager.getSystemLookAndFeelClassName(), true);
    }

    public static Main i() {
        return new Main();
    }


        AtomicInteger a = new AtomicInteger(0);
        static ConcurrentHashMap<String, String> authors = new ConcurrentHashMap<>();
        int funmode = fun ? 1 : 0;
        static {
         authors.put("jiroy1234", "github.com/jiroy1234");
        }


        public void tryShowPopup(String lookandfeelstring, boolean showInfo) throws Exception {
            setTitle("Focal to FOV Converter");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setSize(365, 280);
            setLocationRelativeTo(null);

            Font unifont = new Font("gg sans", Font.PLAIN, 15);
            // this font is used across all of the labels
            Font unifont = new Font("Comic Sans MS", Font.PLAIN, 15);

            String debmode = isDebug() ? "Active ": "Disabled";
            JPanel panelMain = new JPanel();
            GroupLayout layout = new GroupLayout(panelMain);
            panelMain.setLayout(layout);
            String osName = System.getProperty("os.name", "unknown").toLowerCase(Locale.ENGLISH);
            String ver = System.getProperty("os.version");
            UIManager.setLookAndFeel(lookandfeelstring);
            if (showInfo) {
                if (isDebug()) {
                    out.println("current int [debug] (startup): " + a.get());
                }
                if (osName.contains("win")) {
                    osType = "Windows";
                    out.println("user is running on windows!");
                    out.println("sys version: " + ver);
                }
                out.println("ftf version: " + version + " (Since 2/18/2026)");
                out.println("Author(s): " + authors.keySet().stream().toList().getFirst() + " Github: " + authors.values().stream().toList().getFirst());
                out.println("Contributor(s): TurboMaxe Github: github.com/TurboMaxe");
                out.println("debug mode: " + debmode);
                out.println("fun mode: " + fun);
                out.println("label fonts" + unifont.getName() + " size " + unifont.getSize());
                if (osName.contains("mac")) {
                    osType = "macOS";
                    out.println("user is running on mac!");
                    out.println("version: " + ver);
                out.println("fun mode: " + fun );
                out.println("label fonts" + unifont.getName() + " size " + unifont.getSize());
            if (osName.contains("mac")) {
                osType = "macOS";
                out.println("user is running on mac!");
                out.println("version: " + ver);
                } else if (osName.contains("nux") || osName.contains("nix")) {
                    osType = "Linux/Unix";
                    out.println("version: " + ver);
                }
            }

            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            // this font is used across all of the labels
            JLabel widthLabel = new JLabel("Width:");
            JLabel heightLabel = new JLabel("Height:");
            JLabel focalLabel = new JLabel("Focal:");
            JLabel fovLabel = new JLabel("FOV:");
            JButton lookandfeel = new JButton("Change Layout");
            List<JLabel> labels = new ArrayList<>();
            labels.add(widthLabel);
            labels.add(heightLabel);
            labels.add(focalLabel);
            labels.add(fovLabel);

                labels.forEach((label) ->
                    label.setFont(unifont)
            );


            widthInput = new JTextField("36");
            heightInput = new JTextField("24");
            fovOutput = new JTextField();
            fovOutput.setEditable(false);

            focalInput = new JSpinner(new SpinnerNumberModel(50, 1, 300, 1));
            focalSlider = new JSlider(1, 300, 50);

            errorLabel = new JLabel(" ");
            errorLabel.setForeground(Color.RED);
            errorLabel.setFont(Font.getFont("Sans Serif"));

            versionLabel = new JLabel("version " + version);

            Font font = new Font("Sans Serif", Font.PLAIN, 15);
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
                            .addComponent(lookandfeel)
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
                            .addComponent(lookandfeel)
            );

            setContentPane(panelMain);
            setVisible(true);
            focalInput.addChangeListener(e -> {
                focalSlider.setValue((int) focalInput.getValue());
                calculate();
                if (isDebug()) {
                    out.println("User changed focal value!, new value: " + focalInput.getValue());
                }
            });
            SwingUtilities.invokeLater(() ->
            lookandfeel.addActionListener(e -> {
                out.println("Changing layout!");
                if (a.get() == 0) {
                    try {
                        dispose();
                        tryShowPopup(UIManager.getSystemLookAndFeelClassName(), false);
                        SwingUtilities.updateComponentTreeUI(this);
                        this.pack();
                        this.setLocationRelativeTo(null);
                        a.set(a.get() == 0 ? 1 : 0);
                        if (isDebug()) {
                            out.println("current int [debug]: " + a.get());
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (a.get() == 1) {
                    try {
                        dispose();
                        tryShowPopup(UIManager.getSystemLookAndFeelClassName(), false);
                        a.set(a.get() == 0 ? 1 : 0);
                        if (isDebug()) {
                            out.println("current int [debug]: " + a.get());
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }));



            focalSlider.addChangeListener(e -> {
                focalInput.setValue(focalSlider.getValue());
                calculate();
                if (isDebug()) {
                    out.println("User used focal slider!, new value:" + focalSlider.getValue());
                }
            });
            setIconImage(
                    Toolkit.getDefaultToolkit().getImage(
                            Main.class.getResource("/jiroy.png")
                    )
            );
        }

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
            // Changes value to 67 BOI ts so genuinely TUFF onb
            if (focal == 67 && funmode == 1) {
                errorLabel.setText("SIX SEVENNNNN BOIII (focal)");
                return;
            }
            if (width == 67 && funmode == 1) {
                errorLabel.setText("SIX SEVENNNNN BOIII (width)");
                return;
            }
            if (height == 67 && funmode == 1) {
                errorLabel.setText("SIX SEVENNNNN BOIII (height)");
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






