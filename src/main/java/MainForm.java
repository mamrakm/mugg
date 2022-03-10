import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainForm extends JDialog {
    private JButton OKButton;
    private JButton cancelButton;
    private JList<String> list1;
    private JSlider slider1;
    private JPanel contentPane;

    public MainForm() {
        OKButton = new JButton();
        cancelButton = new JButton();
        list1 = new JList();
        slider1 = new JSlider();
        contentPane = new JPanel();

        OKButton.setText("OK");
        cancelButton.setText("Cancel");
        var listData = new Vector<String>();
        listData.add("Jebko");
        listData.add("Kokotko");
        list1.setListData(listData);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(OKButton);

        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.add(OKButton);
        contentPane.add(cancelButton);
        contentPane.add(list1);
        contentPane.add(slider1);

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        // add your code here
        var threadPoolExecutor = Executors.newFixedThreadPool(10);
        var future = threadPoolExecutor.submit(() -> {
            var jFrame = new JFrame();
            jFrame.getWarningString();
            jFrame.setTitle("Kokotko");
            if (!jFrame.isVisible()) jFrame.setVisible(true);
        });
        try {
            future.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        MainForm dialog = new MainForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
