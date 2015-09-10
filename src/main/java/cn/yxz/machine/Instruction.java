package cn.yxz.machine;

public class Instruction {
	/**
	 * 读指令
	 */
	public static final int READ = 10;
	public static final int WRITE = 11;
	public static final int LOAD = 20;
	public static final int STORE = 21;

	public static final int ADD = 30;
	public static final int SUBSTRACT = 31;
	public static final int DIVIDE = 32;
	public static final int MULTIPLY = 33;

	public static final int BRANCH = 40;
	/**
	 * 负数跳转
	 */
	public static final int BRANCH_NEG = 41;
	/**
	 * 等于0跳转
	 */
	public static final int BRANCHN_ZERO = 42;
	public static final int HALT = 43;

	private int data;

	public Instruction(int data) {
		this.data = data;
	}

	public int getValue() {
		return this.data;
	}

	public int getOperationCode() {
		// 前两位
		return this.data / 100;
	}

	public int getOperand() {
		// 后两位
		return this.data % 100;
	}

}
