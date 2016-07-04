package com.xxx.winio.api;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class KeyBoardWin extends JFrame{

	
	public KeyBoardWin(){
		
		this.setTitle("驱动级按键精灵");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.add(new MyPanel());
		this.add(new MyPanel());
		this.add(new MyPanel());
		this.setSize(500, 200);
		this.setVisible(true);
		this.setResizable(false);
	}
	public static void main(String[] args) {
		new KeyBoardWin();
	}
	
	
	class HotKeyThread extends Thread{
		private String key;
		private String windowName;
		private int time;
		private boolean isRunning ;
		public HotKeyThread(String key, int time,String windowName){
			this.key=key;
			this.time=time;
			this.windowName=windowName;
			this.isRunning=true;
		}
		@Override
		public void run() {
			while(isRunning){
				try {
					if(User32.GetWindowText(User32.GetForegroundWindow()).contains(this.windowName)){
						VirtualKeyBoard.KeyPress(VKMapping.toScanCode(this.key));
					}
					sleep(time*100);
					
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
					isRunning =false;
				}
			}
			
		}
		
		public void stopRun(){
			isRunning =false;
		}
	}
	
	class MyPanel extends JPanel{
		JCheckBox checkbox=new JCheckBox("启用");
		JFormattedTextField time=new JFormattedTextField(java.text.NumberFormat.getInstance());
		JTextField windowName=new JTextField("记事本",10);
		JComboBox box=new JComboBox(VKMapping.getKeyName().toArray());
		public MyPanel(){
			this.setLayout(new FlowLayout());
			this.add(checkbox);
			this.add(new JLabel("时间(ms)"));
			time.setColumns(10);
			time.setText("0");
			this.add(time);
			this.add(new JLabel("程序名"));
			this.add(windowName);
			this.add(new JLabel("按键"));
			this.add(box);
			checkbox.addActionListener(new ActionListener() {
				HotKeyThread th;
				@Override
				public void actionPerformed(ActionEvent e) {
					if(checkbox.isSelected()){
						th=new HotKeyThread(box.getSelectedItem().toString(),Integer.parseInt(time.getText()),windowName.getText());
//						th.setPriority(Thread.MAX_PRIORITY);
						th.start();
					}else{
						if(th!=null){
							th.stopRun();
							th=null;
						}
						
					}
				}
			});
		}
	}
}
