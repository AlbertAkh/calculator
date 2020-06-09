package calculator;

import java.awt.*;	//��� �������� ���������-����������� ������� ���������� ������������ ����������
import java.awt.event.*;	//������������ ���������� � ������ ��� ����, ����� ����� ���� � ���������� ������ �������, ���������� ������������ AWT
import javax.swing.*;	//���������� ��� �������� ������������ ����������

public class calculator_main
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() {
                CalculatorFrame frame = new CalculatorFrame();	//�������� ������ ������
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//���������� ������ ��� ������
                frame.setVisible(true);	//������� ��� ��� �����
            }
        });
    }
}
class CalculatorFrame extends JFrame {
    public CalculatorFrame() {
        setTitle("���������� ����������� (����������)");
        CalculatorPanel panel = new CalculatorPanel();
        add(panel);
        pack();
    }
}
class CalculatorPanel extends JPanel {
    public CalculatorPanel() {
        setLayout(new BorderLayout());

        result = 0;
        lastCommand = "=";
        start=true;

        display = new JButton("������� ��������");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);

        ActionListener insert = new InsertAction();	//��������� ��������� ��� ��������� ������� insert
        ActionListener command = new CommandAction();	//��������� ��������� ��� ��������� ������� command

        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));

        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);
        addButton("sin", command);

        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("cos", command);

        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("sqrt", command);

        addButton("0", insert);
        addButton(".", insert);
        addButton("=", command);
      
        add(panel, BorderLayout.CENTER);
    }
    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }
    private class InsertAction implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            String input = event.getActionCommand();
            if(start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }
    private class CommandAction implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            String command = event.getActionCommand();
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start=true;
            }
        }   
     public void calculate(double x)
    {
    
        
        if(lastCommand.equals("sqrt")) result *= Math.sqrt(x);
        else if(lastCommand.equals("cos")) result *= Math.cos(Math.toRadians(x));
        else if(lastCommand.equals("sin")) result *= Math.sin(Math.toRadians(x));
        else if(lastCommand.equals("=")) result = x;
        display.setText("" + result);
    }
    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
}