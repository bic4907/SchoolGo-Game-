import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class EventDialog extends JPanel {

	private ImageIcon img;
	private String message;
	private String userName;
	private String[] messageList;
	private JLabel lblMessage;
	private JLabel lblName;
	private ImageButton btnYes, btnNo;
	private btnListener btnL;
	private Object obj;
	private int eventKind;
	
	public EventDialog(int random) {
		super();
		
		userName = SceneManager.getInstance().getUserName() + " �����! ";
		
		messageList = new String[7];

		messageList[0] = "�ܺο� ��縦 ���� ���Ȧ�� �����ֽðڽ��ϱ�?"; //yes : �� ����, �ູ�� ����
		messageList[1] = "������ �ø��ðڽ��ϱ�?"; //yes : �� ����, �ູ�� ����
		messageList[2] = "���ߵ� ���� ������ �Ͻðڽ��ϱ�?"; //yes : �� ����, �ູ�� ����
		messageList[3] = "��ȯ �л� ���α׷��� �ǽ��Ͻðڽ��ϱ�?"; //yes : �� ����, �ɷ� ����
		messageList[4] = "�ؿ� ��ȸ�� �����Ͻðڽ��ϱ�?"; //yes : �� ����, �ɷ� ����
		messageList[5] = "SW�߽ɴ��п� �����ǽðڽ��ϱ�?"; //yes : �� ����, �ɷ� ����, �ູ�� ����
		messageList[6] = "�б� �򰡴��� �Խ��ϴ�. ������ �����ðڽ��ϱ�?"; //yes : �� ����, �ɷ� ����, �ູ�� ����
		
		this.eventKind = random;
		
		try {
			Image img = ImageIO.read(new File("res/event/" + random + ".png")); //�������� �̹��� ����
			BufferedImage bImg = (BufferedImage)img;
			Image newImage = bImg.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(newImage);
			this.setImg(icon);
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.setMessage(messageList[random]); //�������� ���õ� �̹����� �´� �޽��� ����

		this.setBackground(new Color(255, 200, 0));
		this.setOpaque(true);
		
		render();
	}

	public void render() {
		this.setPreferredSize(new Dimension(500, 320));
		this.setLayout(null);
		
		btnL = new btnListener();
		
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(this.img);
		lblIcon.setBounds(50, 0, 500, 200);

		this.add(lblIcon);

		lblMessage = new JLabel();
		lblMessage.setText(this.message);
		lblMessage.setBounds(10, 230, 500, 30);
		lblMessage.setForeground(Color.black);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblName = new JLabel();
		switch(eventKind) //�̺�Ʈ ������ ���� �ٸ��� ����
		{
		case 0 : lblName.setText(this.userName + "�ܼ�Ʈ�� �����ϴ�!");
			break;
		case 1 : lblName.setText(this.userName + "���� ����� �ʹ� �����ϴ�");
			break;
		case 2 : lblName.setText(this.userName + "���ߵ��� �߻��߽��ϴ�!");
			break;
		case 3 : lblName.setText(this.userName + "�ؿ� �б����� ������ �Խ��ϴ�!");
			break;
		case 4 : lblName.setText(this.userName + "��ȸ�� �ʴ�޾ҽ��ϴ�!");
			break;
		case 5 : lblName.setText(this.userName + "�����մϴ�!");
			break;
		case 6 : lblName.setText(this.userName + "ū�ϳ����ϴ�!");
			break;
		default : lblName.setText(this.userName);	
		}
		lblName.setBounds(10, 200, 500, 30);
		lblName.setForeground(Color.black);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Dotum", Font.BOLD, 15));
		this.add(lblName);
		
		btnYes = new ImageButton();
		btnYes.setNormalImage("res/event/yes_btn_normal.png");
		btnYes.setPressedImage("res/event/yes_btn_pressed.png");
		btnYes.setBounds(170, 270, 80, 30);
		btnYes.setClickSound(SoundPlayer.SoundList.ButtonSound01);
		btnYes.addActionListener(btnL);
		this.add(btnYes);
		
		btnNo = new ImageButton();
		btnNo.setNormalImage("res/event/no_btn_normal.png");
		btnNo.setPressedImage("res/event/no_btn_pressed.png");
		btnNo.setBounds(270, 270, 80, 30);
		btnNo.setClickSound(SoundPlayer.SoundList.ButtonSound01);
		btnNo.addActionListener(btnL);
		this.add(btnNo);
		
		if(this.message.length() > 10) {
			lblMessage.setFont(new Font("Dotum", Font.BOLD, 12));
		} else {
			lblMessage.setFont(new Font("Dotum", Font.BOLD, 15));
		}
		
		this.add(lblMessage);
	}
	
	public String getMessage() {
		return message;
	}
	
	public String[] getMessageList() {return messageList;}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setlblMessage(String message)
	{
		lblMessage.setText(message);
	}
	
	public void setMessage(int random)
	{
		int i;
		
		for(i =0;i<7;i++)
		{
			if(random == i)
			{
				break;
			}
		}
		this.message = messageList[i];
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public void setImg(String path) {
		try {
			Image img = ImageIO.read(new File(path));
			BufferedImage bImg = (BufferedImage)img;
			Image newImage = bImg.getScaledInstance(400,200, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(newImage);
			this.setImg(icon);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object getObj() {return obj;}
	public Object getBtnYes() {return btnYes;}
	public Object getBtnNo() {return btnNo;}
	
	public void SelectedHandling()
	{
		btnYes.doClick(); //���õ��� ���� ��� ���� ����
	} //SelectedHandling()
	
	private class btnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			obj = event.getSource();
			GameManager mgr = SceneManager.getInstance().getGameManager();
			
			if(eventKind == 0)
			{
				if(obj == btnYes)
				{
					mgr.setMoney(mgr.getMoney() + 100000);
					SceneManager.getInstance().addAlert(new AlertDialog("���Ȧ�� �����־����ϴ�"));
				}
				else if(obj == btnNo)
				{
					mgr.applyEffect(new Effect(0, 0, 0, 0, 10, 10, 10, 10, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("���Ȧ�� ��ȭ�ӽ��ϴ�"));
				}
			} //���Ȧ �̺�Ʈ
			else if(eventKind == 1)
			{
				if(obj == btnYes)
				{
					mgr.setMoney(mgr.getMoney() + 100000);
					mgr.applyEffect(new Effect(0, 0, 0, 0, -10, -10, -10, -10, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("������ �ö����ϴ�"));
				}
				else if(obj == btnNo)
				{
					System.out.println("NO!");
					mgr.applyEffect(new Effect(0, 0, 0, 0, 5, 5, 5, 5, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("������ ����Ǿ����ϴ�"));
				}
			} //������ �̺�Ʈ
			else if(eventKind == 2)
			{
				if(obj == btnYes)
				{
					mgr.setMoney(mgr.getMoney() - 50000);
					mgr.applyEffect(new Effect(0, 0, 0, 0, 10, 10, 10, 10, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("���ߵ� ���� ������ �Ͽ����ϴ�"));
				}
				else if(obj == btnNo)
				{
					mgr.applyEffect(new Effect(0, 0, 0, 0, -10, -10, -10, -10, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("���� ��ȭ�� ����ġ�� �ֽ��ϴ�"));
				}
			} //���ߵ� �̺�Ʈ
			else if(eventKind == 3)
			{
				if(obj == btnYes)
				{
					mgr.setMoney(mgr.getMoney() - 50000);
					mgr.applyEffect(new Effect(10, 10, 10, 10, 0, 0, 0, 0, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("��ȯ �л��� ���������ϴ�"));
				}
				else if(obj == btnNo)
				{
					mgr.applyEffect(new Effect(0, 0, 0, 0, -5, -5, -5, -5, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("100% �ѱ��� �л��Դϴ�"));
				}
			} //��ȯ �л� �̺�Ʈ
			else if(eventKind == 4)
			{
				if(obj == btnYes)
				{
					mgr.setMoney(mgr.getMoney() + 50000);
					mgr.applyEffect(new Effect(10, 10, 10, 10, 0, 0, 0, 0, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("�ؿ� ��ȸ�� �����Ͽ����ϴ�"));
				}
				else if(obj == btnNo)
				{
					mgr.applyEffect(new Effect(0, 0, 0, 0, -5, -5, -5, -5, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("��ȸ ��ȸ�� �����߽��ϴ�"));
				}
			} //�ؿ� ��ȸ �̺�Ʈ
			else if(eventKind == 5)
			{
				if(obj == btnYes)
				{
					mgr.setMoney(mgr.getMoney() + 100000);
					mgr.applyEffect(new Effect(0, 0, 10, 0, 0, 0, 10, 0, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("SW�߽ɴ��п� �����Ǿ����ϴ�"));
				}
				else if(obj == btnNo)
				{
					mgr.applyEffect(new Effect(0, 0, 0, 0, -2, -2, -2, -2, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("SW�߽ɴ����� ���� ��ȸ��"));
				}
			} //SW�߽ɴ��� �̺�Ʈ
			else if(eventKind == 6)
			{
				if(obj == btnNo)
				{
					SceneManager.getInstance().addAlert(new AlertDialog("����� ��ȸ������.."));
				}
				else if(obj == btnYes)
				{
					mgr.applyEffect(new Effect(0, 0, 0, 0, -10, -10, -10, -10, "", ""));
					SceneManager.getInstance().addAlert(new AlertDialog("�򰡴��� ǥ���� �������ϴ�"));
				}
			} //�б� �򰡴� �̺�Ʈ
			SceneManager.getInstance().getGameManager().setOccur(false);
		} //actionPerformed()
	} //btnListener class
} //EventDialog class
