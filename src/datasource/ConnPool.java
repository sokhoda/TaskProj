package datasource;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

public class ConnPool {
	private static ConnPool connPool = null;
	private static Queue<Connection> queue;

	private ConnPool(int size) {
		queue = new LinkedList<Connection>();
		for (int i = 0; i < size; i++) {
			queue.add(ConnectionFactory.getInstance().getConnection());
		}
	}

	public Connection getConnection() {
		return queue.poll();
	}

	public void putConnection(Connection connection) {
		queue.add(connection);
	}

	public static ConnPool getInstance(int size) {
		if (connPool == null) {
			connPool = new ConnPool(size);
		}
		return connPool;
	}

	public static Queue<Connection> getQueue() {
		return queue;
	}

	public static void setQueue(Queue<Connection> queue) {
		ConnPool.queue = queue;
	}

}
