import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws InterruptedException, IOException {
        new Test().initUi();

    }
    public void initUi(){
        JFrame frame = new JFrame();
        frame.setTitle("rsndm");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(3);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(new Font("宋体",Font.PLAIN,14));//设置字体，显示格式正常，大小
       //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        //实例化流式布局类的对象
        frame.setLayout(fl);
        //实例化JLabel标签对象，该对象显示“账号”
        JLabel labname = new JLabel("用户：");
        labname.setFont(new Font("宋体",Font.PLAIN,14));
        //将labname标签添加到窗体上
        frame.add(labname);

        //实例化JTextField标签对象化
        JTextField text_name = new JTextField();
        Dimension dim1 = new Dimension(300,30);
        text_name.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //将textName标签添加到窗体上
        frame.add(text_name);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass = new JLabel("数据：");
        labpass.setFont(new Font("宋体",Font.PLAIN,14));
        //将labpass添加到窗体上
        frame.add(labpass);

        //实例化JPasswordField
        JTextField text_password = new JTextField();
        //设置大小
        text_password.setPreferredSize(dim1);
        //添加到窗体
        frame.add(text_password);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labUrl = new JLabel("页面：");
        labUrl.setFont(new Font("宋体",Font.PLAIN,14));
        //将labpass添加到窗体上
        frame.add(labUrl);

        //实例化JPasswordField
        JTextField textField = new JTextField();
        //设置大小
        textField.setPreferredSize(dim1);
        //添加到窗体
        frame.add(textField);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel driverUrl = new JLabel("路径：");
        driverUrl.setFont(new Font("宋体",Font.PLAIN,14));
        //将labpass添加到窗体上
        frame.add(driverUrl);

        //实例化JPasswordField
        JTextField driverText = new JTextField();
        //设置大小
        driverText.setPreferredSize(dim1);
        //添加到窗体
        frame.add(driverText);

        //实例化JButton组件
        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100,30);
        button1.setText("冲刺");
        button1.setFont(new Font("宋体",Font.PLAIN,14));
        //设置按键大小
        button1.setSize(dim2);

        //实例化JButton组件
        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100,30);
        button2.setText("退出");
        button2.setFont(new Font("宋体",Font.PLAIN,14));
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button1);
        frame.add(button2);

        button2.addActionListener(new exit(frame));
        frame.setVisible(true);//窗体可见，一定要放在所有组件加入窗体后
        button1.addActionListener(new go(frame,text_name,textField,text_password,driverText,button1));

    }
    class exit implements ActionListener{
        private javax.swing.JFrame exit;
        public exit(javax.swing.JFrame exit){
            this.exit=exit;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            BiliBili.thread.interrupt();
            exit.dispose();
        }
    }
    class go implements ActionListener {
        private javax.swing.JTextField cookie;
        private javax.swing.JTextField url;
        private javax.swing.JTextField data;
        private javax.swing.JTextField driver;
        private javax.swing.JFrame open;
        private javax.swing.JButton btn;
        public go(javax.swing.JFrame open,javax.swing.JTextField cookie,javax.swing.JTextField url,javax.swing.JTextField data,javax.swing.JTextField driver,JButton jButton)
        {//获取登录界面、账号密码输入框对象
            this.open=open;
            this.url=url;
            this.data=data;
            this.cookie=cookie;
            this.driver=driver;
            this.btn = jButton;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            System.setProperty("webdriver.chrome.driver", driver.getText()+"\\chromedriver.exe");
            btn.setEnabled(false);
            try {
                BiliBili.open(cookie.getText(),data.getText(),url.getText());
            } catch (InterruptedException ex) {
                open.dispose();
            }
        }
    }
}
