package cn.yxz.machine.impl;

import java.util.Scanner;

import cn.yxz.machine.Cpu;
import cn.yxz.machine.Instruction;
import cn.yxz.machine.Memory;
import cn.yxz.machine.MemoryUnit;

public class CpuImpl implements Cpu {
	private Memory mem;

	private boolean isTrace = false;

	public CpuImpl(Memory mem) {
		this.mem = mem;
	}

	/**
	 * 当前指令
	 */
	int register;

	/**
	 * 操作码
	 */
	int operationCode;

	/**
	 * 操作数
	 */
	int operand;

	/**
	 * 下一条指令
	 */
	int nextPointer;

	/**
	 * 累加器
	 */
	int accumulator;

	public int getRegister() {
		return register;
	}

	public void setRegister(int register) {
		this.register = register;
	}

	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	public int getOperand() {
		return operand;
	}

	public void setOperand(int operand) {
		this.operand = operand;
	}

	public int getNextPointer() {
		return nextPointer;
	}

	public void setNextPointer(int nextPointer) {
		this.nextPointer = nextPointer;
	}

	public int getCalculator() {
		return accumulator;
	}

	public void setCalculator(int calculator) {
		this.accumulator = calculator;
	}

	/**
	 * 
	 */
	private void start() {
		// 读取命令，解析命令
		while (true) {
			if (nextPointer >= mem.getMaxSize()) {
				System.out.println("cpu's pointer out Range");
				break;
			}
			MemoryUnit memUnit = mem.access(nextPointer);
			// 解析
			Instruction instruction = new Instruction(memUnit.getData());

			operationCode = instruction.getOperationCode();
			operand = instruction.getOperand();

			switch (operationCode) {

			case Instruction.READ:
				doRead(mem, instruction);
				break;
			case Instruction.WRITE:
				doWrite(mem, instruction);
				break;
			case Instruction.LOAD:
				doLoad(mem, instruction);
				break;

			case Instruction.STORE:
				doStore(mem, instruction);
				break;

			case Instruction.ADD:
				doAdd(mem, instruction);
				break;

			case Instruction.HALT:
				System.out.println("halt!");
				System.exit(0);
				break;
			}
			nextPointer = calcNextPointer(nextPointer, instruction, mem);

			if (isTrace) {
				showTrace();
			}
		}

	}

	private void showTrace() {
		System.out.println("Rigisters:");
		System.out.println("accumulator:" + accumulator);
		System.out.println("pointer:" + nextPointer);
		mem.show();

	}

	private void doAdd(Memory mem2, Instruction instruction) {
		accumulator += mem2.access(operand).getData();
	}

	private void doStore(Memory mem2, Instruction instruction) {
		mem2.store(instruction.getOperand(), this.accumulator);
	}

	private void doLoad(Memory mem2, Instruction instruction) {
		accumulator = mem2.access(instruction.getOperand()).getData();

	}

	private int calcNextPointer(int nextPointer, Instruction instruction,
			Memory mem) {
		switch (operationCode) {
		case Instruction.BRANCH:
			nextPointer = instruction.getOperand(); // 直接跳转到内存制定的单元
		default:
			nextPointer++;
		}
		return nextPointer;
	}

	private void doWrite(Memory mem, Instruction instruction) {
		int memoryAddress = instruction.getOperand();
		MemoryUnit memoryUnit = mem.access(memoryAddress);
		System.out.println("IO Output:" + memoryUnit.getData());

	}

	/**
	 * 以后中断处理
	 * 
	 * @param mem
	 * @param instruction
	 */
	private void doRead(Memory mem, Instruction instruction) {
		System.out.print("please IO input?");
		// int data = System.in.read();
		Scanner scanner = new Scanner(System.in);
		int data = scanner.nextInt();
		
		int memoryAddress = instruction.getOperand();
		mem.store(memoryAddress, data);

	}

	public void reset() {
		// 初始化寄存器
		nextPointer = 0;
		accumulator = 0;
		
		start();
	}

	public void isTrace(boolean isTrace) {
		this.isTrace = isTrace;
	}

	public boolean isTrace() {
		return isTrace;
	}

}
