import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;


@SuppressWarnings("serial")
public class StartScene extends JPanel {

	private JPanel loginPanel;
	private ImageIcon BackImage;
	
	private JLabel lblName;
	private JLabel lblSchool;
	private JLabel lblUniversity;
	
	private JTextField inputName; //����� �̸� �Է�
	private JTextField inputSchool; //����� �б� �Է�
	private ImageButton btnLogin; //�α��� ��ư
	
	@SuppressWarnings("unused")
	private Font font, font2;
	private LoginListener btnL;
	//data
	
	public StartScene() {
		setPreferredSize(new Dimension(800,500));
		setBackground(Color.white);
		setLayout(null);

		SoundPlayer.getInstance().play(SoundPlayer.SoundList.Jjangu); //bgm

		
		try {
			BufferedImage bImg = (BufferedImage)ImageIO.read(new File("res/start/bg_main.jpg"));
			Image newImage = bImg.getScaledInstance(800, 475, Image.SCALE_SMOOTH);
			BackImage = new ImageIcon(newImage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		btnL = new LoginListener();
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/BMHANNAPro.ttf")).deriveFont(40f);
			font2 = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/BMHANNAPro.ttf")).deriveFont(20f);
		} catch (Exception e) {
			e.printStackTrace();
			font = font2 = new Font("Arial", Font.BOLD, 30);
		}

		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBounds(250, 250, 420, 200);
		loginPanel.setOpaque(false);
		add(loginPanel);
		
		lblName = new JLabel("�̸�");
		lblName.setFont(font2);
		lblName.setBounds(10, 0, 50, 50);
		loginPanel.add(lblName);
		
		lblSchool = new JLabel("�б�");
		lblSchool.setFont(font2);
		lblSchool.setBounds(10, 50, 50, 50);
		loginPanel.add(lblSchool);
		
		inputName = new JTextField();
		inputName.setFont(font2);
		inputName.setBounds(60, 10, 220, 30);
		inputName.addActionListener(btnL);
		loginPanel.add(inputName);
		
		inputSchool = new JTextField();
		inputSchool.setFont(font2);
		inputSchool.setBounds(60, 60, 150, 30);
		inputSchool.addActionListener(btnL);
		loginPanel.add(inputSchool);
		
		lblUniversity = new JLabel("���б�");
		lblUniversity.setFont(font2);
		lblUniversity.setBounds(225, 60, 100, 30);
		loginPanel.add(lblUniversity);
		
		btnLogin = new ImageButton();
		btnLogin.setNormalImage("res/start/login_btn_normal.png");
		btnLogin.setPressedImage("res/start/login_btn_pressed.png");
		btnLogin.setClickSound(SoundPlayer.SoundList.ButtonSound01);
		btnLogin.setBounds(90, 100, 140, 50);
		btnLogin.addActionListener(btnL);
		loginPanel.add(btnLogin);
	}//StartScene()
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.drawImage(BackImage.getImage(), 0, 0, this);
	} //paintComponent - ��� �̹��� �׸���
	
	private class LoginListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Object obj = event.getSource();
			int maxLimit = 6;
			int minLimit = 1;
			
			if(obj == btnLogin || obj == inputName || obj == inputSchool)
			{
				if(inputName.getText().length() < minLimit || inputSchool.getText().length() < minLimit)
				{
					SceneManager.getInstance().addAlert(new AlertDialog("�ƹ��͵� �Էµ��� �ʾҽ��ϴ�"));
				} //���� �� �ּ� ����
				else if(inputName.getText().length() > maxLimit )
				{
					SceneManager.getInstance().addAlert(new AlertDialog("�̸��� 6�ڸ� �ʰ��Ͽ����ϴ�."));
				} //���� �� �ִ� ����
				else if(inputSchool.getText().length() > 3)
				{
					SceneManager.getInstance().addAlert(new AlertDialog("�б����� 3�ڸ� �ʰ��Ͽ����ϴ�."));
				} //�б� ���� �� ����
				else if(!inputName.getText().matches("[0-9|a-z|A-Z|��-��|��-��|��-�R]*"))
				{
					SceneManager.getInstance().addAlert(new AlertDialog("Ư�� ���ڴ� �Է��� �� �����ϴ�."));
				} //inputName �� Ư������ �Է� �� ����ó��
				
				else if(!inputSchool.getText().matches("[0-9|a-z|A-Z|��-��|��-��|��-�R]*"))
				{
					SceneManager.getInstance().addAlert(new AlertDialog("Ư�� ���ڴ� �Է��� �� �����ϴ�."));
				} //inputSchool �� Ư������ �Է� �� ����ó��
				else
				{
					SceneManager.getInstance().setUserName(inputName.getText());
					SceneManager.getInstance().setUserSchool(inputSchool.getText());
					
					SceneManager.getInstance().changeState(SceneManager.SceneType.TUTORIAL); //Ʃ�丮�� ȭ������ �Ѿ
					
				} //���� �α���
			} //�α��� ��ư or enter
		} //actionPerformed()
	} //LoginListener class
} //StartScene class
