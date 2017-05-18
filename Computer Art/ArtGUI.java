import java.awt.event.*;  //for ActionListener, ActionEvent
import javax.swing.*;  //for JFrame, BoxLayout, JLabel, JTextField, JButton
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.Image;
import javax.imageio.*;


public class ArtGUI implements ActionListener
{
  private RandomFunction f;
  private JFrame frame;
  private JButton inc, dec, save;
  private int comp;
  private JPanel userStuff;
  private JLabel comptext;
  private ArtPanel canvas;
  
  public static void main(String[] args)
  {
    new ArtGUI();
  }
  
  public ArtGUI()
  {
    f = new RandomFunction(0);
    System.out.println(f.toString());
    comp = f.complexity();
    
    frame = new JFrame();
    frame.setTitle("Computer Art");
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
    userStuff = new JPanel();
    inc = new JButton("+1");
    userStuff.add(inc);
    inc.addActionListener(this);
    dec = new JButton("-1");
    userStuff.add(dec);
    dec.addActionListener(this);
    comptext = new JLabel("Complexity:   "+comp+"      ");
    userStuff.add(comptext);
    save = new JButton("Save");
    userStuff.add(save);
    save.addActionListener(this);
    frame.add(userStuff);
    
    canvas = new ArtPanel();
    frame.add(canvas);
    
    frame.pack();  
    frame.setVisible(true);  
  }
  
  public void actionPerformed(ActionEvent e)
  {
    
    if(e.getSource().equals(inc))
    {
      f.increaseComplexity();
      comp = f.complexity();
    }
    if(e.getSource().equals(dec))
    {
      f.decreaseComplexity();
      comp = f.complexity();
    }
    if(e.getSource().equals(save))
    {
      BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
      canvas.paintAll(image.getGraphics());
      try
      {
        String filename = f.toString()+".png";
        File file = new File("C:/Users/tcario/Desktop/"+filename);
        ImageIO.write(image,"png",file);
      }
      catch(IOException event)
      {
        event.printStackTrace();
      }
      return;
    }
    
    comptext.setText("Complexity:   "+comp+"      ");
    f = new RandomFunction(comp);
    System.out.println(f.toString());
    canvas.paint(canvas.getGraphics());
    
  }
}