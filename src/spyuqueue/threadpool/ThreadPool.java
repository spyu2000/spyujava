package spyuqueue.threadpool;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {

	private LinkedList<ITask> queueList = new LinkedList<ITask>();
	private Worker[] workers = null;
	private ITask task = null;
	private int queueMaxSize = 5000;

	public ThreadPool(int workerNum, int queueMaxSize) {
		this.queueMaxSize = queueMaxSize;
		workers = new Worker[workerNum];
		for (int i = 0; i < workerNum; i++) {
			workers[i] = new Worker("name:" + i);
			workers[i].start();
		}
	}

	public void addTask(ITask task) {
		synchronized (this.queueList) {
			if (this.queueList.size() >= this.queueMaxSize) {
				this.queueList.removeFirst();
			}
			this.queueList.add(task);
			this.queueList.notifyAll();
		}
	}

	public void destory() {
		for (int i = 0; i < workers.length; i++) {
			workers[i].stopWrok();
		}
		synchronized (this.queueList) {
			this.queueList.clear();
		}
	}

	public class Worker extends Thread {
		private boolean isStop = false;
		private ITask task = null;
		boolean isEmpty = false;

		public Worker(String workerName) {
			super(workerName);
		}

		public void run() {
			while (!isStop) {
				synchronized (queueList) {
					try {
						isEmpty = queueList.isEmpty();
						if (isEmpty) {
							queueList.wait();
						} else {
							task = (ITask) queueList.removeFirst();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (task != null) {
					task.execTask();
				}
			}

		}

		public void stopWrok() {
			this.isStop = true;
			synchronized (queueList) {
				queueList.notifyAll();
			}
			this.interrupt();
		}
	}

}
