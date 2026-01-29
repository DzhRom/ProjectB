import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserWindows {

        private JFrame frameUserWindows;
        private JLabel labelUserName = new JLabel("");
        private JLabel balanceUser = new JLabel("");
        private JButton balanceButton = new JButton("Показать баланс");
        private String userName;
        private int balance;


        public void setLabelUserName( String text) {
            labelUserName.setText(text);
            labelUserName.setVisible(true);
        }
        public void setBalance( int balance) {
            this.balance = balance;
        }
        public void setUserName ( String userName) {
            this.userName = userName;
        }




    public UserWindows(String name, int balance, String userName){

        frameUserWindows = new JFrame(name);
        frameUserWindows.setBounds(100, 100, 600, 600);
        frameUserWindows.setLayout(null);
        frameUserWindows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelUserName.setHorizontalAlignment(JLabel.CENTER);
        labelUserName.setVerticalAlignment(JLabel.CENTER);
        labelUserName.setBounds(10, 10, 600, 50);
        frameUserWindows.add(labelUserName);


        balanceButton.setBounds(20, 100, 200, 30);
        frameUserWindows.add(balanceButton);



        setBalance(balance);
        setUserName(userName);

        balanceButton.addActionListener(balanceButtonListener);
        System.out.println(balance);
        System.out.println(userName);
        frameUserWindows.setVisible(true);
    }

    ActionListener balanceButtonListener = new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
                try{
                    balanceUser.setBounds(30, 100, 200, 30);
                    frameUserWindows.add(balanceUser);
                balanceUser.setText(balance + "");
                balanceButton.setVisible(false);
                frameUserWindows.setVisible(true);
            } catch (Exception ex){
                    throw new RuntimeException(ex); }
            }
    };


}
