package cn.yxz.machine;

public class Instruction {

    private OperationCode operationCode;
    private int data;
    private int operand;

    public Instruction(int data) {
        this.data = data;

        parse(data);
    }

    private void parse(int data) {
        operationCode = OperationCode.getByValue(this.data / 100);
        // 后两位
        operand = this.data % 100;
    }

    public int getValue() {
        return this.data;
    }

    public OperationCode getOperationCode() {
        // 前两位
        return this.operationCode;
    }

    public int getOperand() {
        return this.operand;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    public void setOperationCode(OperationCode operationCode) {
        this.operationCode = operationCode;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "data=" + data +
                ", operationCode=" + operationCode +
                ", operand=" + operand +
                '}';
    }
}
