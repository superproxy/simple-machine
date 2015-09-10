package cn.yxz.machine;

import java.util.ArrayList;
import java.util.List;

public class Programe {

	private List<Instruction> instructions = new ArrayList<Instruction>();

	public Programe() {
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public Instruction[] getInstructions() {
		return instructions.toArray(new Instruction[0]);
	}
}
