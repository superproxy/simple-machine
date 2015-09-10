package cn.yxz.machine;

public interface Memory {

	/**
	 * 取内存地址的值
	 * 
	 * @param memoryAddress
	 * @return
	 */
	MemoryUnit access(int memoryAddress);

	/**
	 * 存放数值
	 * 
	 * @param memoryAddress
	 * @param data
	 */
	void store(int memoryAddress, int data);

	void init();

	int getMaxSize();

	void show();


}
