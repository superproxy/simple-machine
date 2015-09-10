package cn.yxz.machine.impl;

import java.util.ArrayList;
import java.util.List;

import cn.yxz.machine.Memory;
import cn.yxz.machine.MemoryUnit;

public class MemoryImpl implements Memory {

	public static int MEMORY_SIZE = 100;
	private List<MemoryUnit> units = new ArrayList<MemoryUnit>(MEMORY_SIZE);

	@Override
	public MemoryUnit access(int memoryAddress) {
		return units.get(memoryAddress);
	}

	@Override
	public void store(int memoryAddress, int data) {
		units.get(memoryAddress).setData(data);
	}

	@Override
	public void init() {
		for (int i = 0; i < MEMORY_SIZE; i++) {
			MemoryUnit unit = new MemoryUnit(0);
			units.add(unit);
		}

	}

	@Override
	public int getMaxSize() {
		return MEMORY_SIZE;
	}

	@Override
	public void show() {
		System.out.println("Memory:");
		int i = 0;
		for (MemoryUnit unit : units) {
			if (i % 10 == 0) {
				System.out.print("\n" + i / 10);
			}
			System.out.print("\t");
			System.out.print(unit.getData());
			System.out.print("\t");
			i++;
		}
		System.out.println("\n");

	}
}
