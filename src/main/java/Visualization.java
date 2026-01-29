import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Visualization {

    public Visualization() {
        // Главное окно

        JFrame frame = new JFrame("Project B");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 600);
        frame.setLayout(null);
        //frame.setVisible(true);
        //Поля ввода
        JTextField loginField = new JTextField();
        JTextField passwordField = new JTextField();
        loginField.setBounds(200, 50, 150, 30);
        passwordField.setBounds(200, 80, 150, 30);
        frame.add(loginField);
        frame.add(passwordField);

        // Заголовки
        JLabel projectBLabel = new JLabel("Вход");
        projectBLabel.setBounds(250, 10, 100, 30);
        JLabel loginLabel = new JLabel("Введите логин: ");
        loginLabel.setBounds(30, 50, 100, 30);
        JLabel passwordLabel = new JLabel("Введите пароль: ");
        passwordLabel.setBounds(30, 80, 150, 30);


        JLabel resultLLogInLabel = new JLabel("");
        resultLLogInLabel.setBounds(30, 200, 400, 30);

        frame.add(projectBLabel);
        frame.add(loginLabel);
        frame.add(passwordLabel);


        frame.add(resultLLogInLabel);

        //Кнопка Войти
        JButton logInButton = new JButton("Войти");
        logInButton.setBounds(250, 150, 100, 30);
        frame.add(logInButton);

        //Обработчик нажатия кнопки Войти
        ActionListener logInButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Authorization auth = new Authorization();
                try {
                    auth.logInUser(loginField.getText(), passwordField.getText());
                    if (auth.getName() != null) {
                        UserWindows userWindows = new UserWindows("Привет " + auth.getName(),  auth.getBalance(), auth.getName());
                        userWindows.setLabelUserName("Приветствую, " + auth.getName());
                        frame.dispose();
                    } else resultLLogInLabel.setText(auth.logInUser(loginField.getText(), passwordField.getText()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }



                /**
                Authorization auth = new Authorization();
                try {
                    resultLLogInLabel.setText(auth.logInUser(loginField.getText(), passwordField.getText()));
                    if (auth.getName() != null) {
                        JFrame frameAuth = new JFrame(auth.getName());
                        frameAuth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frameAuth.setBounds(100, 100, 600, 600);
                        frameAuth.setLayout(null);
                        resultLLogInLabel.setBounds(100, 50, 400, 30);
                        frameAuth.add(resultLLogInLabel);
                        frame.setVisible(false);
                        frameAuth.setVisible(true);
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                 */
            }
        };

        logInButton.addActionListener(logInButtonListener);


        frame.setVisible(true);
    }
}
