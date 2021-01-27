
package chatting.application;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements ActionListener {
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Server(){
        p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        add(p1);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("chatting/application/Icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);//Image ki size ke liye
        ImageIcon i3=new ImageIcon(i2);
        JLabel l1=new JLabel(i3);// we cant put any image directly so we used this label
        l1.setBounds(5,17,30,30);
        p1.add(l1);// add finction is used to add any element on frame 
        l1.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });
        
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("chatting/application/Icons/video.png"));
        Image i5=i4.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);//Image ki size ke liye
        ImageIcon i6=new ImageIcon(i5);
        JLabel l2=new JLabel(i6);// we cant put any image directly so we used this label
        l1.setBounds(290,20,30,30);
        p1.add(l2);// add without this wont print anything
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("chatting/application/Icons/phone.png"));
        Image i8=i7.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT);//Image ki size ke liye
        ImageIcon i9=new ImageIcon(i5);
        JLabel l5=new JLabel(i9);// we cant put any image directly so we used this label
        l5.setBounds(350,20,35,30);
        p1.add(l5);// add without this wont print anything
        
        ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("chatting/application/Icons/3icon.png"));
        Image i12=i11.getImage().getScaledInstance(15,25,Image.SCALE_DEFAULT);//Image ki size ke liye
        ImageIcon i13=new ImageIcon(i12);
        JLabel l6=new JLabel(i13);// we cant put any image directly so we used this label
        l6.setBounds(410,20,13,25);
        p1.add(l6);// add without this wont print anything
        
        
        ImageIcon i14=new ImageIcon(ClassLoader.getSystemResource("chatting/application/Icons/1.png"));
        Image i15=i14.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);//Image ki size ke liye
        ImageIcon i16=new ImageIcon(i15);
        JLabel l7=new JLabel(i16);// we cant put any image directly so we used this label
        l7.setBounds(40,5,60,60);
        p1.add(l7);// add without this wont print anything
        
        
        
        JLabel l3=new JLabel("Lalita");
        l3.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110,15,100,18);
        p1.add(l3);
        
        JLabel l4=new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110,35,100,20);
        p1.add(l4);
        
        a1=new JTextArea();
        a1.setBounds(5,75,440,570);
        a1.setBackground(Color.PINK);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));//jo bhi msg print hone wla uska font big krne ke liye
        a1.setEditable(false);// you can't edit anything after sending msg
        add(a1);
       
        t1=new JTextField();//Tetx Are whwree user type message
        t1.setBounds(5,655,310,40);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        add(t1);
        
        b1=new JButton("send");
        b1.setBounds(320,655,123,40);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.white);
        b1.setFont(new Font("SAN_SERIF",Font.BOLD,16));
         b1.addActionListener(this);
         a1.setLineWrap(true);// when msg are big, they just go out of text area so to put long msg on new line we used this function
        a1.setWrapStyleWord(true);
         add(b1);
      
        getContentPane().setBackground(Color.YELLOW);//used to apply affext on entire frame likebackground color chane
        setLayout(null);
        setSize(450,700);//Frame size
        setLocation(400,200);
        //setUndecorated(true);//max mini remove
        setVisible(true);//This should write at the end
    }
    public void actionPerformed(ActionEvent ae){
        try{
        String out=t1.getText(); //Msg text are me print karne ke liye
        a1.setText(a1.getText()+"\n\t\t\t"+out);// old msgs print as well as new in down \t to print meassage on right
       dout.writeUTF(out);
        t1.setText("");//to make text area empty after sending message
        }catch(Exception e){}
    
    }
    public static void main(String args[]){
        new Server().setVisible(true);
        String msginput="";
        try{
         skt=new ServerSocket(6001); //socket number hai undar
        s=skt.accept();
        din=new DataInputStream(s.getInputStream());// jo bhi data aayega usme stored hoga
        dout=new DataOutputStream(s.getOutputStream());
        msginput=din.readUTF();
        a1.setText(a1.getText()+"\n"+msginput);
        skt.close();
        s.close();
        
        
        }catch(Exception e){}
    }
}
