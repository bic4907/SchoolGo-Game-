import java.awt.Point;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class GameManager {

	private int nMonth, nDay, nHumanCount, nRandomDay;
	private long nMoney, lastIncome;

	private Vector<Student> nStudent;
	private Vector<Professor> nProfessor;
	private boolean flag_scouting;
	
	private Vector<Building> buildingList;
	private Vector<Building> nBuilding;
	public Vector<String> CollegeType;
	
	private Vector<HumanWalking> humanList;
	private int randomList[];
	private boolean isOccur;
	
	public enum PersonType {
		Student, Professor
	}
	
	public enum ResultType {
		Perfect, OK, Inmun, Jayon, Gonggwa, Yeche, Fail, Pasan
		// Perfect : 3�� �̻��� �ܰ������� ����ġ�� �Ѿ��� ���
		// OK	   : 2�� �̻��� �ܰ������� ����ġ�� �Ѿ��� ���
		// Imnum   : �ι��븸 ����ġ�� �Ѿ��� ���
		// Jayon   : �ڿ��븸 ����ġ�� �Ѿ��� ���
		// Gonggwa : �����븸 ����ġ�� �Ѿ��� ���
		// Yeche   : ��ü�ɸ� ����ġ�� �Ѿ��� ���
		// Fail    : ����ġ�� ���� ������ 0���� ���
	    // Pasan   : ������ �����Ͽ� ������ �������� �Ǿ��� ��
	}
	
	
	public GameManager() {
		// 2�� 1�Ϻ��� ���ӽ���
		nMonth = 2;
		nDay = 0;
		nMoney = 1000;
		lastIncome = 0;
		isOccur = false;
		nRandomDay = (int)(Math.random() * 19) + 5;
		
		buildingList = new Vector<Building>();
		humanList = new Vector<HumanWalking>(); // humanList
		randomList = new int[7];
		for(int i=0;i<7;i++)
			randomList[i] = -1;
		
		/* ���� �߰��ϴ� ��*/
		{
			Building b = new Building("�����", 300000, 80, new Point(125, 75), "res/building/g1.png");
			Effect e = new Effect(0, 1.0, 0, 0, 0, 0, 0.2, 0, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("�̳뺣�̼Ǽ���", 1000000, 150, new Point(225, 50), "res/building/g2.png");
			Effect e = new Effect(0, 0, 1.6, 0, 0, 0, 0.8, 0, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("���ǰ�", 300000, 50, new Point(475, 50), "res/building/j1.png");
			Effect e = new Effect(0, 1.0, 1.0, 0, 0, 0.3, 0.3, 0, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("�湫��", 300000, 50, new Point(600, 45), "res/building/j2.png");
			Effect e = new Effect(0, 1.0, 1.0, 0, 0, 0.3, 0.3, 0, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("�������", 800000, 120, new Point(100, 260), "res/building/i1.png");
			Effect e = new Effect(0.8, 0.8, 0.8, 0.8, 0.3, 0.1, 0.1, 0.1, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("������", 300000, 50, new Point(140, 180), "res/building/i2.png");
			Effect e = new Effect(0.8, 0, 0, 0, 0.8, 0, 0, 0, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("�����", 350000, 50, new Point(540, 180), "res/building/y1.png");
			Effect e = new Effect(0, 0, 0, 0.8, 0, 0, 0, 0.3, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("����Ȧ", 300000, 50, new Point(600, 280), "res/building/y2.png");
			Effect e = new Effect(0, 0, 0, 0, 0.4, 0.5, 0.5, 0.4, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("�����", 200000, 50, new Point(350, 50), "res/building/p2.png");
			Effect e = new Effect(0, 0, 0, 0, 0.2, 0.2, 0.2, 0.2, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("CU", 100000, 20, new Point(410, 103), "res/building/p4.png");
			Effect e = new Effect(0, 0, 0, 0, 0.3, 0.3, 0.3, 0.3, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("ü����", 200000, 10, new Point(280, 160), "res/building/p3.png");
			Effect e = new Effect(0, 0, 0, 0.5, 0, 0, 0, 0.5, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		{
			Building b = new Building("���", 100000, 10, new Point(380, 210), "res/building/p1.png");
			Effect e = new Effect(-0.8, -0.8, -0.8, -0.8, 1, 1, 1, 1, "", "");
			b.addEffect(e);
			buildingList.add(b);
		}
		/* ���� �߰� �ϴ� �� �� */

		nBuilding = new Vector<Building>();
		nBuilding.add(new Building("�����̳�", 1, 50, new Point(188, 330), "res/building/container.png"));
		
		nStudent = new Vector<Student>();
		nProfessor = new Vector<Professor>();
		nProfessor.add(new Professor("Natural"));
		
		this.CollegeType = new Vector<String>();
		this.CollegeType.add("�ι���");
		this.CollegeType.add("�ڿ���");
		this.CollegeType.add("������");
		this.CollegeType.add("��ü�ɴ�");
		

	} 
	
	public long getMoney(){return nMoney;}
	public void setMoney(long Money) {nMoney = Money;}
	public int[] getRandomList() {return randomList;}

	// get/set method
	
	public void nextDay() {
		this.nDay++;
		if(this.nDay > 30) {
			this.nDay = 1;
			this.nMonth++;
			this.isOccur = false; //�̹� �޿��� ���� ���� �̺�Ʈ �߻����� �ʾ���
			this.nRandomDay = (int)(Math.random() * 19) + 5; //���� �ٲ�� ���� �̺�Ʈ �߻� ��¥�� �ٽ� ����
		}
		nextStep();
	}
	
	public void nextStep() {
		int money = 0;
		
		/* �� ���� */
		int num = this.nStudent.size();
		money += num * 300;
		num = this.nProfessor.size();
		money += num * -1000;
		num = this.nBuilding.size() - 1;
		money += num * -5000;
		this.lastIncome = money;
		this.nMoney += money;
		
		/* �ູ�� ���� */
		for(Student s : this.nStudent) {
			if(s.getHappyness() == 0) continue;
			s.setHappyness(s.getHappyness() - 0.1);
			if(s.getHappyness() < 0) s.setHappyness(0);
		}
		/* ���� ���� */
		
		HashMap<String, Integer> cntProfessor = new HashMap<String, Integer>();
		for(String type : this.CollegeType) {
			cntProfessor.put(type, 1);
		}
		
		for(Professor f : this.nProfessor) {
			if(f.getCollege() == "Natural") continue;
			
			cntProfessor.put(f.getCollege(), cntProfessor.get(f.college) + 1);
		}
		
		for(Student s : this.nStudent) {
			s.setKnowledge(s.getKnowledge() + (cntProfessor.get(s.college) / 30));
			if(s.getKnowledge() < 0) s.setKnowledge(0);
		}	
		
		
		
		Effect totalEffect = new Effect();
		/* �ǹ� ȿ�� ���� */
		for(Building b : this.nBuilding) {
			
			Vector<Effect> b_e = b.getEffects();
			
			
			for(Effect e : b_e) {
				
				for(String type : this.CollegeType) {
					
					totalEffect.setHappyness(type,
							totalEffect.getHappyness(type) +
							e.getHappyness(type)
							);
					totalEffect.setKnowledge(type,
							totalEffect.getKnowledge(type) +
							e.getKnowledge(type)
							);
				}
				
			}
		}

		for(Student s : this.nStudent) {
			s.setHappyness(s.getHappyness() + totalEffect.getHappyness(s.getCollege()));
			s.setKnowledge(s.getKnowledge() + totalEffect.getKnowledge(s.getCollege()));
		}	
		
		
		
		
		if(nMoney <= -500000) {
			SceneManager.getInstance().changeState(SceneManager.SceneType.RESULT);
		}

	}
	
	
	public int getMonth() {
		return this.nMonth;
	}
	public int getDay() {
		return this.nDay;
	}


	public Vector<Building> getBuildingList() {
		return this.buildingList;
	}

	public boolean buyBuilding(String name) {
		// TODO ��üũ �� �ǹ�����
		
		
		for(Building b : buildingList) {
			if(b.getName() == name) {
				if(b.getPrice() <= this.nMoney) {
					nBuilding.add(new Building(b));
					this.nMoney -= b.getPrice();
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public Vector<Building> getMyBuilding() {
		return this.nBuilding;
	}


	public void scoutPerson(PersonType type, String text) {
		if(type == PersonType.Student) {
			Random r = new Random();
			int num = r.nextInt(20) + 20;
			
			int allCount = this.getStudentCount();
			int capacity = this.getAllCapacity();
			int margin = 0;
			
			if(allCount + num > capacity) { // �л��� ���ļ� ���ư��� �ϴ� ���
				margin = allCount + num - capacity;
				num = capacity - allCount;
			}
			
			for(int i = 0; i < num; i++) {
				this.nStudent.add(new Student(text));
			}
			
			if(num == 0){
				SceneManager.getInstance().addAlert(new AlertDialog("������ ��á���ϴ�"));
			} // ������ �� á�� ��
			else if(margin > 0) {
				SceneManager.getInstance().addAlert(new AlertDialog(text + " " + Integer.toString(num) + "���� �̾ҽ��ϴ�"));
				SceneManager.getInstance().addAlert(new AlertDialog(Integer.toString(margin) + "���� �ڸ��� ��� ���ư�"));
			} // ������ �������� �� ���� ����� ��
			else
			{
				SceneManager.getInstance().addAlert(new AlertDialog(text + " " + Integer.toString(num) + "���� �̾ҽ��ϴ�"));
				//�޸ո���Ʈ �߰�
			} // ���������� �������� ��
			
			if( ((allCount+num) / 30) > nHumanCount ) // 30���� ��� 1�� �߰�
			{
				HumanWalking hw = new HumanWalking(nHumanCount);
				humanList.add(hw);
			
				nHumanCount++;
			} // if
		} else if(type == PersonType.Professor) {
			Random r = new Random();
			int num = r.nextInt(3) + 5;
			

			for(int i = 0; i < num; i++) {
				this.nProfessor.add(new Professor(text));
			}
			
			SceneManager.getInstance().addAlert(new AlertDialog(text + " ���� " + Integer.toString(num) + "���� �̾ҽ��ϴ�"));

		}
	}


	public boolean getScouting() {
		return flag_scouting;
	}

	public void setScouting(boolean flag_scouting) {
		this.flag_scouting = flag_scouting;
	}

	public int getStudentCount() {
		return this.nStudent.size();
	}
	public int getProfessorCount() {
		return this.nProfessor.size();
	}
	
	public int getAllCapacity() {
		int cnt = 0;
		for(Building b : this.nBuilding) {
			cnt += b.getCapacity();
		}
		cnt -= this.nProfessor.size();
		return cnt;
		
	}

	public long getLastIncome() {
		return this.lastIncome;
	}
	
	public void applyEffect(Effect e) {
		
		for(Student s : this.nStudent) {
			s.setHappyness(s.getHappyness() + e.getHappyness(s.getCollege()));
			s.setKnowledge(s.getKnowledge() + e.getKnowledge(s.getCollege()));
		}

	}
	
	public Result getResult() {
		// Perfect : 3�� �̻��� �ܰ������� ����ġ�� �Ѿ��� ���
		// OK	   : 2�� �̻��� �ܰ������� ����ġ�� �Ѿ��� ���
		// Imnum   : �ι��븸 ����ġ�� �Ѿ��� ���
		// Jayon   : �ڿ��븸 ����ġ�� �Ѿ��� ���
		// Gonggwa : �����븸 ����ġ�� �Ѿ��� ���
		// Yeche   : ��ü�ɸ� ����ġ�� �Ѿ��� ���
		// Fail    : ����ġ�� ���� ������ 0���� ���
	    // Pasan   : ������ �����Ͽ� ������ �������� �Ǿ��� ��
		HashMap<String, Double> avg_knowledge, avg_happyness;
		HashMap<String, Boolean> passed = new HashMap<String, Boolean>();
		HashMap<String, Integer> cnt_student = new HashMap<String, Integer>();
		avg_knowledge = new HashMap<String, Double>();
		avg_happyness = new HashMap<String, Double>();
		
		// 1. ���� �ʱ�ȭ
		for(String s : this.CollegeType) {
			avg_knowledge.put(s, (double)0);
			avg_happyness.put(s, (double)0);
			passed.put(s, false);
			cnt_student.put(s, 1);
		}
		
		// 2. ��ü �� ���ϱ�
		for(Student s : nStudent) {
			avg_knowledge.put(s.getCollege(), avg_knowledge.get(s.getCollege()) + s.getKnowledge());
			avg_happyness.put(s.getCollege(), avg_happyness.get(s.getCollege()) + s.getHappyness());
			cnt_student.put(s.getCollege(), cnt_student.get(s.getCollege()) + 1);
		}
		
		// 3. ��� ���ϱ�
		int cnt_pass = 0;
		for(String s : this.CollegeType) {

			avg_knowledge.put(s, avg_knowledge.get(s) / cnt_student.get(s));
			avg_happyness.put(s, avg_happyness.get(s) / cnt_student.get(s));
			System.out.println(s + "/[Knowledge] " + avg_knowledge.get(s) + "/[Happyness] " + avg_happyness.get(s));			
			if(avg_knowledge.get(s) >= 60.0 && avg_happyness.get(s) >= 70.0) {
				cnt_pass++;
				passed.put(s, true);
			}
		}
		
		// 4. ��� ������
		
		Result result = new Result();
		
		if(this.nMoney > -500000) {
			if(cnt_pass >= 3) {
				// Perfect
				result.type = ResultType.Perfect;
			} else if(cnt_pass == 2) {
				// �׳� ������ ���
				result.type = ResultType.OK;
			} else if(cnt_pass == 1) {
				// �Ѱ����� ������ ���
				if(passed.get("�ι���") == true) {
					result.type = ResultType.Inmun;
				} else if(passed.get("�ڿ���") == true) {
					result.type = ResultType.Jayon;
				} else if(passed.get("������") == true) {
					result.type = ResultType.Gonggwa;
				} else if(passed.get("��ü�ɴ�") == true) {
					result.type = ResultType.Yeche;
				}
	
			} else {
				// Fail
				result.type = ResultType.Fail;
			}
			
			// �󼼰�� �߰�
			
			for(String s : this.CollegeType) {
				String college_desc = "";
				
				college_desc = s;
				
				college_desc += "  ���ļ��� ";
				if(avg_knowledge.get(s) >= 0) {
					college_desc += "���� ";
				} else {
					college_desc += "���� ";
				}
				
				college_desc += "�ູ�� ";
				if(avg_happyness.get(s) >= 0) {
					college_desc += "���� ";
				} else {
					college_desc += "���� ";
				}
								
				result.desc.add(college_desc);
			}
		} else {
			result.type = ResultType.Pasan;
		}
	
		return result;
	}
	
	public Vector<HumanWalking> getMyHuman(){
		return this.humanList;
	}

	public boolean getOccur() {
		return isOccur;
	}

	public void setOccur(boolean isOccur) {
		this.isOccur = isOccur;
	}

	public int getRandomDay() {
		return nRandomDay;
	}

	public void setRandomDay(int nRandomDay) {
		this.nRandomDay = nRandomDay;
	}	
	
	
	
}
