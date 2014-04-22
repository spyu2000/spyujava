package spyuqueue.threadpool;

import java.io.File;

public class TestExample {

	private static ThreadPool pool = new ThreadPool(10, 1000);

	public static void main(String[] args) {
		// 找车扩展名是doc的文件
		File file = new File("c:\\");
		File file1 = new File("d:\\");
		pool.addTask(new TestScanTask(file, ".doc"));
		pool.addTask(new TestScanTask(file1, ".doc"));
	}

}
