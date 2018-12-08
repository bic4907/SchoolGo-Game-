import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class IngameScene extends JPanel {
	

	private ImageIcon iconMoney, iconTime, iconStud, iconProf, iconEntr;
	private ImageButton btnIStud, btnIProf, btnIBuild; // �̹�����ư
	private JPanel infoPanel; // �������� ��� �г�
	private JLabel lblTime, lblMoney, lblStud, lblProf, lblEntr, lblSName; // ��������
	private CollegeSelectDialog scoutDlg;
	
	
	private ImageIcon backIcon = new ImageIcon("res/IngameScene2.png"); // ���

	private BtnListener btnL;
	
	private GameThread gameThd;
	private HumanWalkingThread hwThd;
	public IngameScene() {
		GameManager manager = SceneManager.getInstance().getGameManager();
		
		setPreferredSize(new Dimension(800,500)); // ũ��
		setLayout(null);

		iconMoney = new ImageIcon("res/lblicon/money.png"); // label�� �߰��� �� ������
		iconTime = new ImageIcon("res/lblicon/time.png"); // label�� �߰��� �ð� ������
		iconStud = new ImageIcon("res/lblicon/stud.png"); // label�� �߰��� �л� ������
		iconProf = new ImageIcon("res/lblicon/prof.png"); // label�� �߰��� ���� ������
		iconEntr = new ImageIcon("res/lblicon/entr.png"); // �б� ������

		Font customFont2, customFont1;
		try {
			customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/BMHANNAPro.ttf")).deriveFont(18f);
			customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/BMHANNAPro.ttf")).deriveFont(14f);
		} catch (Exception e) {
			e.printStackTrace();
			customFont2 = new Font("Arial", Font.BOLD, 30);
			customFont1 = new Font("mFont", Font.BOLD, 12);
		}	// JLabel ��Ʈ
		
		btnL = new BtnListener(); // buttonListener
		
		infoPanel = new JPanel(); // Label��� Panel 
		infoPanel.setBounds(100,20,430,55);
		infoPanel.setBackground(Color.ORANGE);
		infoPanel.setLayout(null);
		this.add(infoPanel);
	
		
		lblTime = new JLabel();
		lblTime.setBounds(10,5,250,20);
		lblTime.setFont(customFont2);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setText(String.valueOf(manager.getMonth()) 
				+ "/" +
				String.valueOf(manager.getDay()));
		lblTime.setIcon(iconTime);
		infoPanel.add(lblTime);
		
		lblMoney = new JLabel(""+ manager.getMoney(),iconMoney,SwingConstants.LEFT);
		lblMoney.setBounds(10,30,250,20);
		lblMoney.setFont(customFont2);
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblMoney);
		// ��
		
		lblStud = new JLabel("",iconStud,SwingConstants.LEFT);
		lblStud.setBounds(220,5,200,20);
		lblStud.setFont(customFont2);
		lblStud.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblStud);
		// �л� ��
		
		lblProf = new JLabel("",iconProf,SwingConstants.LEFT);
		lblProf.setBounds(220,30,200,20);
		lblProf.setFont(customFont2);
		lblProf.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblProf);
		// ���� ��
		
		btnIBuild = new ImageButton();
		btnIBuild.setNormalImage("res/Ingame/BUILDB.png");
		btnIBuild.setPressedImage("res/Ingame/BUILDB2.png");
		btnIBuild.setBounds(650,10,100,100);
		btnIBuild.addActionListener(btnL);
		add(btnIBuild);
		// �ǹ� �Ǽ� ��ư
		
		btnIStud = new ImageButton();
		btnIStud.setNormalImage("res/Ingame/STUDB.png");
		btnIStud.setPressedImage("res/Ingame/STUDB2.png");
		btnIStud.setBounds(15, 90, 100, 50);
		btnIStud.addActionListener(btnL);
		add(btnIStud);
		// �л� ���� ��ư
		
		btnIProf = new ImageButton();
		btnIProf.setNormalImage("res/Ingame/PROFB.png");
		btnIProf.setPressedImage("res/Ingame/PROFB2.png");
		btnIProf.setBounds(15, 140, 100, 50);
		btnIProf.addActionListener(btnL);
		add(btnIProf);
		// ���� ���� ��ư


		lblSName = new JLabel();
		lblSName.setOpaque(true);
		lblSName.setBackground(Color.white);
		lblSName.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
		lblSName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSName.setFont(customFont1);
		lblSName.setBounds(20,70,82,20);
		add(lblSName); // �б��̸� �߰�
		
		lblEntr = new JLabel(iconEntr);
		lblEntr.setBounds(10, 0, 100, 100);
		add(lblEntr);
		
		
		// ���� �л��̳� ���� ������ư�� ������ ����â�� �����
		scoutDlg = new CollegeSelectDialog();
		scoutDlg.setBounds(200, 260, 400, 100);
		scoutDlg.setVisible(false);
		this.add(scoutDlg);		
		
		
		if(gameThd == null) {
			gameThd = new GameThread();
			gameThd.start();
		}
		if(hwThd == null) {
			hwThd = new HumanWalkingThread();
			hwThd.start();
		}
		SoundPlayer.getInstance().play(SoundPlayer.SoundList.Ludibriam);
		
		render();
	}

	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		GameManager manager = SceneManager.getInstance().getGameManager();		
		if(manager.getScouting()) {
			scoutDlg.setVisible(true);
		} else {
			scoutDlg.setVisible(false);
		}
			
		
		page.drawImage(backIcon.getImage(),0,0,this);
	} // ���ȭ�� �߰�
	
	public void render() {
		GameManager manager = SceneManager.getInstance().getGameManager();		
		

		lblSName.setText(SceneManager.getInstance().getUserSchool()+"���б�");
		
		lblTime.setText(String.valueOf(manager.getMonth()) + "�� " + String.valueOf(manager.getDay()) + "��");
		
		if((manager.getDay() > 1 && manager.getDay() <= 5) || (manager.getDay() > 15 && manager.getDay() <= 20)) {
			this.btnIStud.setVisible(true);
			this.btnIProf.setVisible(true);
		} else {
			this.btnIStud.setVisible(false);
			this.btnIProf.setVisible(false);
		}
		if(manager.getDay() == 1 || manager.getDay() == 15) {
			SceneManager.getInstance().addAlert(new AlertDialog("�� ���������� ���۵˴ϴ�!"));
		}

		if(manager.getOccur() == false) //�� �޿� �ѹ�
		{
			if((manager.getDay() >= 5) && (manager.getDay() <= 23)) //5�� ~ 23�� ���̿� ����
			{	
				if(manager.getDay() == manager.getRandomDay())
				{
					int random = (int)(Math.random()*7); //���� �̺�Ʈ
					
					for(int i = 0;i<7;i++)
					{
						if(manager.getRandomList()[i] == -1)
						{
							manager.getRandomList()[i] = random; 
							break;
						} //�̺�Ʈ�� �̹� �߻������� �˱� ���� �迭�� �ش� �̺�Ʈ�� ���� 
						
						else if(manager.getRandomList()[i] == random)
						{
							while(manager.getRandomList()[i] == random)
							{
								random = (int)(Math.random()*7);
							}
						} //�̹� �߻��� �̺�Ʈ�� �߻����� �ʵ�����
					}
	
					EventDialog ed = new EventDialog(random);
					manager.setOccur(true);
					SceneManager.getInstance().addEvent(ed);
				}
			} // ���� �̺�Ʈ �߻�
		}		
		
		this.lblMoney.setText(String.format("%,d", manager.getMoney()) + "(" + String.format("%+,d", manager.getLastIncome()) + ")");
		this.lblStud.setText(Integer.toString(manager.getStudentCount())
				+ "/" + Integer.toString(manager.getAllCapacity())
				);
		this.lblProf.setText(Integer.toString(manager.getProfessorCount()));
		
		
		for(Building b : manager.getMyBuilding()) {
			
			JLabel bIcon = new JLabel();

			bIcon.setBounds(b.getposition().x, b.getposition().y, 100, 100);
			
			Image img;
			try {
				img = ImageIO.read(new File(b.getImage().toString()));
				BufferedImage bImg = (BufferedImage)img;
				Image newImage = bImg.getScaledInstance(bIcon.getWidth(), bIcon.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(newImage);
				bIcon.setIcon(icon);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			this.add(bIcon);
		} // �ǹ������س��� �迭, �ǹ� �׸���
		
		for(HumanWalking data : manager.getMyHuman()) {
			if(data.attached == false) {
				add(data);
				add(data.getEventPanel());
				data.attached = true;
			}
		} // ��� �׸���
		
		
		if(manager.getScouting()) {
			scoutDlg.setVisible(true);
		} else {
			scoutDlg.setVisible(false);
		}
		
		repaint();

	}
	
	public CollegeSelectDialog getScoutDlg() {
		return this.scoutDlg;
	}
	
	private class BtnListener implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent event)
		{
			GameManager manager = SceneManager.getInstance().getGameManager();
			
			Object obj = event.getSource();
			if(obj == btnIStud)
			{
				manager.setScouting(true);
				scoutDlg.setVisible(true);
				scoutDlg.setType(GameManager.PersonType.Student);				
			} // �л�����
			
			else if(obj == btnIProf)
			{
				manager.setScouting(true);
				scoutDlg.setVisible(true);
				scoutDlg.setType(GameManager.PersonType.Professor);
			} // ��������
			
			else if(obj == btnIBuild)	
			{
				if(gameThd != null && gameThd.isAlive()) {
					gameThd.stop();
				}
				gameThd = null;
				
				SceneManager.getInstance().changeState(SceneManager.SceneType.BUILD);
				
			} // �ǹ��Ǽ�
		}
		
	}
	
	
	
	
	
	/* 1�ʸ��� ���ӽð��� ����� */
	
	private class GameThread extends Thread {
		
		@Override
		public void run() {
			GameManager gm = SceneManager.getInstance().getGameManager();
			while(true) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				gm.nextDay();
				render();
				
				if(gm.getMonth() >= 7 || gm.getMoney() <= -500000) {
					break;
				}
			}
			SceneManager.getInstance().changeState(SceneManager.SceneType.RESULT);
			
		}
	}
	
	private class HumanWalkingThread extends Thread {
		
		@Override
		public void run() {
			
			GameManager gm = SceneManager.getInstance().getGameManager();
			while(true) {
				for(HumanWalking hw : gm.getMyHuman()) {
					hw.setLocation(hw.x, hw.y);
					hw.repaint();
					hw.rEvent.repaint();
					repaint();
				}
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					break;

				}
			}
		}
	}
	
	
	
	
	
	
	
}
