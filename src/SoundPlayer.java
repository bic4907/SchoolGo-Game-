import java.io.File;
import java.util.EnumMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

	private static SoundPlayer instance;
	
	public static SoundPlayer getInstance() {
		if(instance == null) {
			instance = new SoundPlayer();	
		}
		return instance;
	}

	
	public enum SoundList {
		Jjangu, MapleMain, Ellinia, Curning, Ludibriam, ButtonSound01
	}
	
	private Map<SoundList, String> soundPath;
	private Map<SoundList, Clip> clips;
	private SoundList nowPlay;
	
	public SoundPlayer() {
		
		/*
		 * EnumMap ����
		 * https://stackoverflow.com/questions/12669497/using-enum-as-key-for-map
		 * */
		soundPath = new EnumMap<SoundList, String>(SoundList.class);
		clips = new EnumMap<SoundList, Clip>(SoundList.class);
		
		
		/* ���� ��� �ʱ�ȭ */
		soundPath.put(SoundList.MapleMain, 	"�����ø���.wav");
		soundPath.put(SoundList.Jjangu, 	"¯��.wav");
		soundPath.put(SoundList.Ellinia, 	"�����Ͼ�.wav");
		soundPath.put(SoundList.Curning, 	"Ŀ�׽�Ƽ.wav");
		soundPath.put(SoundList.Ludibriam,  "���긮��.wav");
		soundPath.put(SoundList.ButtonSound01,  "btn_sound.wav");
		
		clips.clear();
	}
	
	private final String bgmDir = "res/bgm/";
	
	public void play(SoundList s) {

		
		if(clips.get(s) == null) {

				File f = new File(bgmDir + soundPath.get(s));
				try {
				AudioInputStream stream = AudioSystem.getAudioInputStream(f);
				Clip clip = AudioSystem.getClip();
				clip.open(stream);
				clips.put(s, clip);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		
		if(nowPlay != null) {
			this.stop(nowPlay);
		}		
		//clips.get(s).setFramePosition(10);		
		clips.get(s).loop(Clip.LOOP_CONTINUOUSLY);
		
		nowPlay = s;
	}

	public void playOnce(SoundList s) {
		File f = new File(bgmDir + soundPath.get(s));
		try {
		AudioInputStream stream = AudioSystem.getAudioInputStream(f);
		Clip clip = AudioSystem.getClip();
		clip.open(stream);
		clip.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop(SoundList s) {
		if(clips.get(s) != null) {
			clips.get(s).stop();
		}
	}

}
