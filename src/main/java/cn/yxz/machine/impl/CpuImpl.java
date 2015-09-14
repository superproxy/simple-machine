package cn.yxz.machine.impl;

import cn.yxz.machine.*;

import java.util.Scanner;

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
    OperationCode operationCode;

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

    public OperationCode getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(OperationCode operationCode) {
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
        showUsage();
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
            System.out.println("instruction:" + instruction);

            switch (operationCode) {

                case READ:
                    doRead(mem, instruction);
                    break;
                case WRITE:
                    doWrite(mem, instruction);
                    break;
                case LOAD:
                    doLoad(mem, instruction);
                    break;

                case STORE:
                    doStore(mem, instruction);
                    break;

                case ADD:
                    doAdd(mem, instruction);
                    break;

                case HALT:
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

    private void showUsage() {
        StringBuilder sb = new StringBuilder();
        sb.append("READ:10\t");
        sb.append("WRITE:11\t");
        sb.append("LOAD:20\t");
        sb.append("STORE:21\t");
        sb.append("ADD:30\t");
        sb.append("SUBSTRACT:31\t");
        sb.append("DIVIDE:32\t");
        sb.append("MULTIPLY:33\t");
        sb.append("BRANCH:40\t");

        System.out.println(sb.toString());
    }

    private void showTrace() {
        // 指令说明


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
            case BRANCH:
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
