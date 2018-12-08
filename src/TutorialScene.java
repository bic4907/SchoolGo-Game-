import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TutorialScene extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int pageNum;
	private Vector<Slide> slides;
	
	
	private JLabel		lblSceneTitle;
	private JLabel		lblCurrent;
	private JLabel		lblTotal;
	
	private ImageButton		btnPrevSlide;
	private ImageButton		btnNextSlide;
	private ImageButton		btnSkip;
	private JLabel 		slideImg;
	private JLabel		slideDesc;
	
	private Image bgImage;
	
	private SlideButtonListener slideBtnL;
	
	private SlideThread sThd;
	private boolean flag_anime;
	
	public TutorialScene() {
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		/* Init the Slides */
		this.slides = new Vector<Slide>();
		this.slides.add(new Slide("res/tutorial/t1.jpg", "<html><p>��Ŵ� 1��, 15�ϸ��� Ȱ��ȭ<br>�Ǵ� �л�/���� ���� ��ư<br>Ŭ���� ���� �л�, ���� ���� ����<br>�л� �� ���� �� ���� ����<br>���� ������ �о��� ����, �� ����<br><br>�谢 �ܰ��� �� ���� ��ư<br>�ܰ��븶�� ������ �ɷ�ġ�� �ٸ�<br></p></html>"));
		this.slides.add(new Slide("res/tutorial/t2.jpg", "<html><p>������ ���� ��Ȳ<br>�ð��� ���� �̺�Ʈ�� �߻�<br>7���� �Ǹ� ��������<br>�л� ���� ���� ���� ǥ��<br>���� ���� �ִ� ���� ���� ǥ��<br><br>���л����� �������� ����� �̺�Ʈ<br>å�� Ŭ���ϸ� �ɷ�ġ�� ����<br>��Ʈ Ŭ���� �л� �ູ�� ����</p></html>"));
		this.slides.add(new Slide("res/tutorial/t3.jpg", "<html><p>��Ǽ��Ǿ� �ִ� �ǹ�<br>�ǹ��� ���� �л�����, �ɷ�ġ ����<br>�ǹ� �Ǽ����, ������� �Ҹ�<br><br>��ǹ� �Ǽ� ��ư<br>Ŭ���� �ǹ��Ǽ� ȭ������ �̵�</p></html>"));
		this.slides.add(new Slide("res/tutorial/t4.jpg", "<html><p>��ǹ� �Ǽ� ��ư<br>�ǹ� ���ݰ� �þ�� �л����� ǥ��<br>Ŭ�� �� ���ӿ� �ǹ� �Ǽ��ϰ� ����ȭ������ �̵�<br><br>��ǹ� �Ǽ� ȭ�� ������ ��ư<br>Ŭ���� �ǹ� �Ǽ� ���� �ʰ� ����ȭ������ �̵�</p></html>"));
		this.slides.add(new Slide("res/tutorial/t5.jpg", "<html><p>�緣�� �̺�Ʈ<br>�������� �̺�Ʈ�� ����<br>�� �̺�Ʈ���� ���ӿ� ������ �ִ°� �ٸ�<br><br>���̺�Ʈ ���� ��ư<br>�÷��̾��� ���ÿ� ���� �ɷ�ġ, ���� ����� ������ ��</p></html>"));
		pageNum = 0;
		flag_anime = false;
		
		// �����̵� ��ư ������
		slideBtnL = new SlideButtonListener();				
		
		/* ���� �����̵�� ��ư */
		btnPrevSlide = new ImageButton();
		btnPrevSlide.setNormalImage("res/prev_btn_normal.png");
		btnPrevSlide.setPressedImage("res/prev_btn_pressed.png");
		btnPrevSlide.setBounds(50, 370, 100, 60);
		btnPrevSlide.setClickSound(SoundPlayer.SoundList.ButtonSound01);
		btnPrevSlide.addActionListener(slideBtnL);
		this.add(btnPrevSlide);		
		
		/* ���� �����̵�� ��ư */
		btnNextSlide = new ImageButton();
		btnNextSlide.setNormalImage("res/next_btn_normal.png");
		btnNextSlide.setPressedImage("res/next_btn_pressed.png");
		btnNextSlide.setBounds(650, 370, 100, 60);
		btnNextSlide.setClickSound(SoundPlayer.SoundList.ButtonSound01);
		btnNextSlide.addActionListener(slideBtnL);
		this.add(btnNextSlide);
		
		/* Ʃ������ ��ŵ ��ư */
		btnSkip = new ImageButton();
		btnSkip.setNormalImage("res/skip_btn_normal.png");
		btnSkip.setPressedImage("res/skip_btn_pressed.png");
		btnSkip.setBounds(650, 20, 100, 60);
		btnSkip.setClickSound(SoundPlayer.SoundList.ButtonSound01);
		btnSkip.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent event) {
				SceneManager.getInstance().changeState(SceneManager.SceneType.INGAME);
			}
		});
		this.add(btnSkip);
		
		
		/* �̹��� �����̵� */
		slideImg = new JLabel();
		slideImg.setBounds(90, 120, 350, 220);
		slideImg.setOpaque(true);
		slideImg.setForeground(Color.RED);
		slideImg.setBackground(new Color(0, 0, 0, 200));
		this.add(slideImg);
		
		/* �ؽ�Ʈ �����̵� */
		slideDesc = new JLabel();
		slideDesc.setFont(new Font("Dotum", Font.BOLD, 15));
		slideDesc.setBounds(480, 140, 250, 160);
		slideDesc.setBackground(Color.GRAY);
		this.add(this.slideDesc);

		/*
		 *  Ŀ���� ��Ʈ ���� �ּ�
			https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
		*/
		Font customFont, customFont2;
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/BMHANNAPro.ttf")).deriveFont(40f);
			customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/BMHANNAPro.ttf")).deriveFont(20f);
		} catch (Exception e) {
			e.printStackTrace();
			customFont = customFont2 = new Font("Arial", Font.BOLD, 30);
		}		
		
	

		/* Tutorial �۾� */
		lblSceneTitle = new JLabel("Ʃ�丮��");
		lblSceneTitle.setFont(customFont);
		lblSceneTitle.setBounds(330, 10, 200, 50);
		this.add(lblSceneTitle);
		
		/* ��ü �����̵� ǥ�� �� */
		lblTotal = new JLabel();
		lblTotal.setFont(customFont2);
		lblTotal.setBounds(400, 380, 200, 50);
		lblTotal.setText("/ " + this.slides.size());
		this.add(lblTotal);
		
		/* ���� ������ ǥ�� �� */
		lblCurrent = new JLabel();
		lblCurrent.setFont(customFont2);
		lblCurrent.setBounds(380, 380, 200, 50);
		this.add(lblCurrent);
		
		refreshSlide();
		
		SoundPlayer.getInstance().play(SoundPlayer.SoundList.Ellinia);
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		
		if(bgImage == null) {
			try {
				bgImage = ImageIO.read(new File("res/tutorial/bg.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(bgImage != null) {
			page.drawImage(bgImage, 0, 0, this);
		}
	}
	
	
	private class Slide {
		private ImageIcon image;
		private String desc;
		
		public Slide(String src, String desc) {
			this.image = new ImageIcon(src);
			this.desc = desc;
		}
		
		public String getImagePath() {
			return this.image.toString();
		}
		
		@SuppressWarnings("unused")
		public ImageIcon getImage() {
			return this.image;
		}
		public String getDesc() {
			return this.desc;
		}
	}
	
	private class SlideButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnPrevSlide && !flag_anime) {
				pageNum--;
				flag_anime = true; // ����,���� �����̵� ��ư�� ��� �Ұ����ϰ� �����.
				sThd = new SlideThread();
				sThd.start();
			} else if(e.getSource() == btnNextSlide && !flag_anime) {
				pageNum++;
				flag_anime = true; // ����,���� �����̵� ��ư�� ��� �Ұ����ϰ� �����.
				sThd = new SlideThread();
				sThd.start();
			}
		} 
	}

	public void refreshSlide() {
		Slide s = this.slides.get(pageNum);

		try {
			BufferedImage bImg = null;
			bImg = ImageIO.read(new File(s.getImagePath()));
			Image img = bImg.getScaledInstance(slideImg.getWidth(), slideImg.getHeight(), Image.SCALE_SMOOTH);
			slideImg.setIcon(new ImageIcon(img));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		slideDesc.setText(s.getDesc());
		lblCurrent.setText(String.valueOf(pageNum + 1)); // ���� ������ ����
		
		btnPrevSlide.setVisible(true);
		btnNextSlide.setVisible(true);
		
		if(pageNum == 0) {
			btnPrevSlide.setVisible(false);
		} else if(pageNum == this.slides.size() - 1) {
			btnNextSlide.setVisible(false);
		}
	}
	
	private class SlideThread extends Thread {
		
		@Override
		public void run() {
			
			int init_img = slideImg.getX();
			int init_desc = slideDesc.getX();
			
			boolean is_change = false;
			for(double i = 0; i < Math.PI; i += 0.01) {
				if(!is_change && i > Math.PI / 2) {
					refreshSlide();
					is_change = true;
				}
				int dist = (int)(Math.sin(i) * 500);

				slideImg.setLocation(init_img - dist, slideImg.getY());
				slideDesc.setLocation(init_desc + dist, slideDesc.getY());
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			slideImg.setLocation(init_img, slideImg.getY());
			slideDesc.setLocation(init_desc, slideDesc.getY());
			
			flag_anime = false; // ����,���� �����̵� ��ư�� �ٽ� ��� �����ϰ� �����.
		}
	}
	
}
