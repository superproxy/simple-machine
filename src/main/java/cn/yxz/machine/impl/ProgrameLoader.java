package cn.yxz.machine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cn.yxz.machine.Instruction;
import cn.yxz.machine.Programe;

public class ProgrameLoader {

	public static Programe readPrograme(InputStream in) {

		List<Instruction> instructions = new ArrayList<Instruction>();
		Scanner scanner = new Scanner(in);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (line == null || line.trim().equals("")) {
				continue;
			}
			if (line.startsWith("#")) {
				continue;
			}

			try {
				int data = Integer.valueOf(line);
				Instruction instrucation = new Instruction(data);
				instructions.add(instrucation);
			} catch (NumberFormatException ex) {
				System.out.println(line);
				ex.printStackTrace();
			}
		}

		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Programe p = new Programe();
		p.setInstructions(instructions);
		return p;

	}

}
