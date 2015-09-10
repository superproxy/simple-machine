package cn.yxz.machine;

import cn.yxz.machine.Machine;
import cn.yxz.machine.impl.MachineImpl;

public class App {
public static void main(String[] args) {
	   Machine  machine = new MachineImpl();
	   machine.powerOn();
}
}
