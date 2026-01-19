import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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
    /*
     * the current version of ftf
     *
     * @param version the version of ftf, circles are round
     * @return returns the version to display gui
     */
    @Getter private String version = "1.2.3";




    public Main() {
        /*
         * application logic
         *
         */
        setContentPane(panelMain);
        setTitle("Focal to FOV converter ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(365, 165);
        setLocationRelativeTo(null);
        setVisible(true);




        /*
        * uh change this version string
        * whenever an updating the project
        */


        versionLabel.setText( "version " + getVersion());

        focalInput.addChangeListener(changeEvent -> {
            focalSlider.setValue((int) focalInput.getValue());
            calculate();
        });
        focalSlider.addChangeListener(changeEvent -> {
            focalInput.setValue(focalSlider.getValue());
            calculate();
        });
    }


    private static boolean isZero(float width) {
        return width == 0;
    }



    void calculate() {
        float result;
        final int sensorX = 36;
        final int sensorY = 24;
        final float sensor = (float) sensorX / sensorY;
        final int focal = (int) focalInput.getValue();
        float width;
        float height;

        try {
            width = Float.parseFloat(widthInput.getText());
            height = Float.parseFloat(heightInput.getText());
        } catch (Exception e) {
            errorLabel.setText("Invalid width/height input.");
            return;
        }
        if (width == 0 || height == 0) {
            errorLabel.setText("Invalid Width/Height.");
            return;
        }
        else {

            errorLabel.setText("");

        }

        if (focal == 0) {
            fovOutput.setText("Undefined");
            errorLabel.setText("Invalid focal value.");
            return;
        }

        /* remove this if you are
         * the fun police
         */

        if (focal == 67 || width == 67 || height == 67) {
            errorLabel.setText("SIX SEVENNNNNN");
            return;
       }


        if (width / height >= sensor) {
            result = (float) ((float) (180 / Math.PI) * 2 * Math.atan((double) (sensorX / width * height) / (2 * focal)));
        } else {
            result = (float) ((float) (180 / Math.PI) * 2 * Math.atan((double) sensorY / (2 * focal)));
        }

        fovOutput.setText("" + result);
        errorLabel.setText(" ");

    }


    public static void main(String[] args) {
        FlatLightLaf.setup();
        new Main();
    }
}
