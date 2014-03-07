import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JMXClientMain {

	public static void main(String[] args) {
		JMXServiceURL jmxUrl;
		try {
			jmxUrl = new JMXServiceURL(
					"service:jmx:rmi:///jndi/rmi://127.0.0.1:8991/jmxrmi");

			JMXConnector jmxConn = JMXConnectorFactory.connect(jmxUrl);
			MBeanServerConnection mbsc = jmxConn.getMBeanServerConnection();
			MemoryMXBean mbean = ManagementFactory.newPlatformMXBeanProxy(mbsc,
					ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);
			OperatingSystemMXBean mbeancpu = ManagementFactory.newPlatformMXBeanProxy(
					mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
					OperatingSystemMXBean.class);
			MemoryUsage memoryUsage = mbean.getHeapMemoryUsage();
			System.out.println("Used Memmory Amount :: "
					+ memoryUsage.getUsed() / 1024 / 1024);
			System.out.println("Max Memmory Amount ::" + memoryUsage.getMax()
					/ 1024 / 1024);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
