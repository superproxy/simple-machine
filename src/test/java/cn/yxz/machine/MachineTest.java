package cn.yxz.machine;

import cn.yxz.machine.impl.MachineImpl;
import org.testng.annotations.Test;

public class MachineTest {

    @Test
    public void test() {
        Machine machine = new MachineImpl();
        machine.powerOn();
    }
}
