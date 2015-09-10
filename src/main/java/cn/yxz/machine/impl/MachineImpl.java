package cn.yxz.machine.impl;

import cn.yxz.machine.*;

import java.io.InputStream;

public class MachineImpl implements Machine {

    private static void loadProgrameInMemory(Programe p, Memory mem) {
        int i = 0;
        for (Instruction instruction : p.getInstructions()) {
            mem.store(i++, instruction.getValue());
        }
    }

    Cpu cpu;
    Memory mem;

    @Override
    public void powerOn() {
        mem = new MemoryImpl();
        cpu = new CpuImpl(mem);
        cpu.isTrace(true);
        mem.init();


        // 加载到指定内存，开始执行
        InputStream in = this.getClass().getResourceAsStream("/instruction.ml");
        Programe p = ProgrameLoader.readPrograme(in);
        loadProgrameInMemory(p, mem);

        cpu.reset();
    }

    @Override
    public void powerOff() {

    }

    @Override
    public void reset() {
        cpu.reset();

    }
}
