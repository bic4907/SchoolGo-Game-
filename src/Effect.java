import java.util.HashMap;

public class Effect {
	
	private HashMap<String, Double> knowledge;
	private HashMap<String, Double> happyness;
	private String name;
	private String desc;
	
	public Effect() {
		knowledge = new HashMap<String, Double>();
		happyness = new HashMap<String, Double>();
	
		this.name = "";
		this.desc = "";
		
		for(String type : SceneManager.getInstance().getGameManager().CollegeType) {
			knowledge.put(type, (double)0);
			happyness.put(type, (double)0);
		}
	}

	public Effect(double i, double j, double k, double l, double m, double n, double o, double p, String nm, String d) {
		knowledge = new HashMap<String, Double>();
		happyness = new HashMap<String, Double>();
	
		this.name = nm;
		this.desc = d;
		
		this.knowledge.put("�ι���", i);
		this.knowledge.put("�ڿ���", j);
		this.knowledge.put("������", k);
		this.knowledge.put("��ü�ɴ�", l);
		
		this.happyness.put("�ι���", m);
		this.happyness.put("�ڿ���", n);
		this.happyness.put("������", o);
		this.happyness.put("��ü�ɴ�", p);
		
	}	
	
	public double getHappyness(String type) {
		return this.happyness.get(type);	
	}	
	public double setHappyness(String type, double v) {
		if(!SceneManager.getInstance().getGameManager().CollegeType.contains(type)) {
			System.out.println("Wrong key");
		}
		return this.happyness.put(type, v);
	}
	public double getKnowledge(String type) {
		return this.knowledge.get(type);	
	}	
	public double setKnowledge(String type, double v) {
		if(!SceneManager.getInstance().getGameManager().CollegeType.contains(type)) {
			System.out.println("Wrong key");
		}
		return this.knowledge.put(type, v);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String str = "";
		
		str += "[Knowledge]";
		str += "�ι��� :";
		str += this.knowledge.get("�ι���");
		str += "�ڿ��� :";
		str += this.knowledge.get("�ڿ���");
		str += "������ :";
		str += this.knowledge.get("������");
		str += "��ü�ɴ� :";
		str += this.knowledge.get("��ü�ɴ�");
		
		str += "[Happyness]";
		str += "�ι��� :";
		str += this.happyness.get("�ι���");
		str += "�ڿ��� :";
		str += this.happyness.get("�ڿ���");
		str += "������ :";
		str += this.happyness.get("������");
		str += "��ü�ɴ� :";
		str += this.happyness.get("��ü�ɴ�");
		
		return str;
		
	}
	
}
