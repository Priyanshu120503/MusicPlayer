
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class test implements MouseListener {
	JFrame frame = new JFrame();
	JLabel start = new JLabel();
	//JButton stop = new JButton("Stop");
	JLabel prev = new JLabel();
	JLabel next = new JLabel();
	JLabel add = new JLabel();
	JPanel buttonPanel = new JPanel();
	ImageIcon playIcon, pauseIcon, nextIcon, prevIcon, addIcon;
	DoublyLinkedList<song> s;
	public test() throws IOException, LineUnavailableException, UnsupportedAudioFileException 
	{
		s = new DoublyLinkedList<>();
			
		s.addFirst(new song("D:\\Songs\\Wav\\Go Down Deh.wav"));
		s.addLast(new song("D:\\Songs\\Wav\\Bhool Bhulaiyaa 2 (Title Track).wav"));
		s.addLast(new song("D:\\Songs\\Wav\\Ahzee King Official.wav"));

		playIcon = new ImageIcon("C:\\Users\\imrat\\eclipse-workspace\\Music_Player\\Icons\\play-button2.png");
		pauseIcon = new ImageIcon("C:\\Users\\imrat\\eclipse-workspace\\Music_Player\\Icons\\pause2.png");
		nextIcon = new ImageIcon("C:\\Users\\imrat\\eclipse-workspace\\Music_Player\\Icons\\next2.png");
		prevIcon = new ImageIcon("C:\\Users\\imrat\\eclipse-workspace\\Music_Player\\Icons\\prev2.png");
		addIcon = new ImageIcon("C:\\Users\\imrat\\eclipse-workspace\\Music_Player\\Icons\\add2.png");
		
		start.addMouseListener(this);
		//stop.addActionListener(this);
		prev.addMouseListener(this);
		next.addMouseListener(this);
		add.addMouseListener(this);
		
//		start.setBounds(187, 5, 125, 125);
//		prev.setBounds(5, 5 , 125, 125);
//		next.setBounds(367, 5 , 125, 125);
		
		start.setIcon(playIcon);
		prev.setIcon(prevIcon);
		next.setIcon(nextIcon);
		add.setIcon(addIcon);
		
//		start.setBackground(Color.cyan);
//		prev.setBackground(Color.cyan);
//		next.setBackground(Color.cyan);
		
//		start.setOpaque(true);
//		prev.setOpaque(true);
//		next.setOpaque(true);
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		buttonPanel.setBackground(new Color(139, 215, 240));
		buttonPanel.setPreferredSize(new Dimension(500, 135));
		
		
//		frame.add(stop);
		buttonPanel.add(prev);
		buttonPanel.add(start);
		buttonPanel.add(next);
		buttonPanel.add(add);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setTitle("Music Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,500);
		frame.setMinimumSize(new Dimension(800, 500));
		frame.getContentPane().setBackground(Color.white);
		frame.setLocationRelativeTo(null);
//		frame.setLayout(null);
		frame.setVisible(true);
		
		s.createTempNodeAtFirst();
	}
	
	private int p = 1;
	private int q = 1;
	@Override
	public void mouseClicked(MouseEvent e)
	{		
		if(e.getSource() == start)
		{
			if(p == 1)
			{
				p = 0;
				q = 1;
				start.setIcon(pauseIcon);
				s.getTempElement().run(1);				
			}			
			else if(p == 0)
			{
				p = 1;
				q = 0;
				start.setIcon(playIcon);
				s.getTempElement().run(0);				
			}
		}
		else if(e.getSource() == prev)
		{
			s.getTempElement().run(2);
			s.getTempElement().run(0);
			if(s.tempCanMoveBack())
			{
				s.moveTempBack();
				s.getTempElement().run(q);
			}
			else 
			{
				s.getTempElement().run(0);	
				p = 1;
				start.setIcon(playIcon);
			}
		}
		else if(e.getSource() == next)
		{
			s.getTempElement().run(2);
			s.getTempElement().run(0);
			if(s.tempCanMoveAhead())
			{
				s.moveTempAhead();
				s.getTempElement().run(q);
			}
			else
			{
				s.getTempElement().run(0);
				p = 1;
				start.setIcon(playIcon);
			}
		}	
		else if(e.getSource() == add)
		{
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(null);
			if(response == JFileChooser.APPROVE_OPTION)
			{
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				String p = file.toString();
				if(p.endsWith(".wav"))
				{
					try 
					{
						s.addLast(new song(file.toString()));
					} 
					catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
			
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
	}
	
	@Override 
	public void mouseEntered(MouseEvent e)
	{
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}
