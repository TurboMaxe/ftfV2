import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

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
    private JTextField heightInput;

    public Main() {
        setContentPane(panelMain);
        setTitle("Focal to FOV");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(360,240);
        setLocationRelativeTo(null);
        setVisible(true);

        focalInput.addChangeListener(changeEvent -> {
            focalSlider.setValue((int)focalInput.getValue());
            calculate();
        });
        focalSlider.addChangeListener(changeEvent -> {
            focalInput.setValue(focalSlider.getValue());
            calculate();
        });
    }

     private static boolean isZero(int n) {
        return n == 0;
        }
         
    void calculate() {
        float result;
        final int sensorX = 36;
        final int sensorY = 24;
        final float sensor = (float) sensorX / sensorY;
        final int focal = (int) focalInput.getValue();
        final float width;
        final float height;

        try {
            width = Float.parseFloat(widthInput.getText());
            height = Float.parseFloat(heightInput.getText());
        } catch (Exception e) {
            errorLabel.setText("Invalid width/height input.");
            return;
        }

        if (isZero(focal)) {
            fovOutput.setText("Undefined");
            errorLabel.setText("Specify a focal value other than 0.");
            return;
        }
        if (width == 0 || height == 0) {
            errorLabel.setText("Specify width/height value other than 0.");
            return;
        }

        if (width/height >= sensor) {
            result = (float) ((float) (180/Math.PI) * 2 * Math.atan((double) (sensorX / width * height) / (2 * focal)));
        } else {
            result = (float) ((float) (180/Math.PI) * 2 * Math.atan((double) sensorY / (2 * focal)));
        }

        fovOutput.setText("" + result);
        errorLabel.setText(" ");
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new Main();
    }
}
