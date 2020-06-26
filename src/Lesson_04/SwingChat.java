package Lesson_04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingChat extends JFrame {
    private final JTextArea publicFrame = new JTextArea();
    private final JTextField userInput = new JTextField();

    public SwingChat(){
        setTitle("Swing-реализация");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(50,50,800,600);

        setLayout(new BorderLayout());
        JPanel topContainer = new JPanel(new GridLayout());
        add(topContainer, BorderLayout.CENTER);

        JPanel bottomContainer = new JPanel(new BorderLayout());
        add(bottomContainer, BorderLayout.SOUTH);

        topContainer.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(2,2,2,2),
                        BorderFactory.createLineBorder(Color.gray)
                )
        );
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(0,2,2,2));

        topContainer.add(new JScrollPane(publicFrame));
        bottomContainer.add(userInput,BorderLayout.CENTER);
        JButton buttonSend = new JButton("Отправить");
        bottomContainer.add(buttonSend, BorderLayout.EAST);

        publicFrame.setEditable(false);
        publicFrame.setLineWrap(true);
        publicFrame.setWrapStyleWord(true);
        publicFrame.setMargin(new Insets(2,5,2,5));

        userInput.setMargin(new Insets(2,5,2,5));

        userInput.addActionListener(this :: sendMessage);
        buttonSend.addActionListener(this :: sendMessage);

        setVisible(true);
    }

    private void sendMessage(ActionEvent e){
        publicFrame.append(userInput.getText() + "\n");
        userInput.setText(null);
        userInput.requestFocus();
    }
}
