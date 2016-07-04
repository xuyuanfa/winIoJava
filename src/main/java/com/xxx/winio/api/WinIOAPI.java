package com.xxx.winio.api;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;


public class WinIOAPI {
	public static final int CONTROL_PORT = 0x64;
	public static final int DATA_PORT = 0x60;
	static{
		System.loadLibrary("WinIo32");
		JNative.setLoggingEnabled(true);
		if(!InitializeWinIo()){
			System.err.println("Cannot Initialize the WinIO");
			System.exit(1);
		}
		
	}
	public static void main(String[] args) throws Exception {
//		
//		Thread.sleep(3000);
//		KeyDown(VK_0);
//		Thread.sleep(10000);
//		
//		KeyUp(VK_0);
//		
//		
	}
	
	
	
	public static void KBCWait4IBE() throws Exception{
		int val=0;
		do {
			Pointer p=new Pointer(MemoryBlockFactory.createMemoryBlock(8));
			if(!GetPortVal(CONTROL_PORT,p, 1)){
				System.err.println("Cannot get the Port");
			}
			val = p.getAsInt(0);
			
		} while ((0x2&val)>0);
		
		
		
		
	}
	
	public static boolean InitializeWinIo() {
		
		try{
		 JNative jnative = new JNative("WinIo32","InitializeWinIo");
		 jnative.setRetVal(Type.INT);
		 jnative.invoke();
		 int re=jnative.getRetValAsInt();
		 jnative.dispose();
		 return re>0;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			
		}
		 
	}
	
	
	public static boolean GetPortVal(int portAddr, Pointer pPortVal, int size) throws Exception{
		
		JNative j2 = new JNative("WinIo32","GetPortVal");
		 j2.setRetVal(Type.INT);
		 
		 j2.setParameter(0, portAddr);
		 j2.setParameter(1, pPortVal);
		 j2.setParameter(2, size);
		 j2.invoke();
		 int re=j2.getRetValAsInt();
		 j2.dispose();
		 return re>0;
		
	}
	
	public static boolean SetPortVal(int portAddr, int portVal, int size) throws Exception{
		
		JNative j2 = new JNative("WinIo32","SetPortVal");
		 j2.setRetVal(Type.INT);
		 j2.setParameter(0, portAddr);
		 j2.setParameter(1,portVal);
		 j2.setParameter(2, size);
		 j2.invoke();
		 int re=j2.getRetValAsInt();
		 j2.dispose();
		 return re>0;
		
	}
	

}
