package com.xxx.winio.jna;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface WinIo64 extends StdCallLibrary {
	WinIo64 INSTANCE = (WinIo64) Native.loadLibrary("WinIo64", WinIo64.class, W32APIOptions.DEFAULT_OPTIONS);

	int CONTROL_PORT = 0x64;
	int DATA_PORT = 0x60;

	boolean InitializeWinIo();

	void ShutdownWinIo();

	boolean InstallWinIoDriver(String pszWinIoDriverPath, boolean IsDemandLoaded);

	boolean GetPortVal(int portAddr, Pointer pPortVal, int size);

	boolean SetPortVal(int portAddr, int portVal, int size);
}
