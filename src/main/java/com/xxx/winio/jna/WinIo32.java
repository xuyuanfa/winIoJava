package com.xxx.winio.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.W32APIOptions;

public interface WinIo32 extends Library {
	WinIo32 INSTANCE = (WinIo32) Native.loadLibrary("WinIo32", WinIo32.class, W32APIOptions.DEFAULT_OPTIONS);

	int CONTROL_PORT = 0x64;
	int DATA_PORT = 0x60;

	boolean InitializeWinIo();

	void ShutdownWinIo();

	boolean GetPortVal(int portAddr, Pointer pPortVal, int size);

	boolean SetPortVal(int portAddr, int portVal, int size);
}
