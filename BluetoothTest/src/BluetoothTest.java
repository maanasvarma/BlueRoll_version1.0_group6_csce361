import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.*;

public class BluetoothTest {
	public static final Vector<RemoteDevice> devices = new Vector<RemoteDevice>();
	
	public static Vector<RemoteDevice> discoverDevices() throws IOException, InterruptedException{
		final Object inquiryCompletedEvent = new Object();
		
		devices.clear();
		
		DiscoveryListener discListener = new DiscoveryListener(){
			public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod){
				System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
                devices.addElement(btDevice);
                try {
                    System.out.println("     name " + btDevice.getFriendlyName(false));
                } catch (IOException cantGetDeviceName) {
                }
			}
			
			public void inquiryCompleted(int discType) {
                System.out.println("Device Inquiry completed!");
                synchronized(inquiryCompletedEvent){
                    inquiryCompletedEvent.notifyAll();
                }
            }

            public void serviceSearchCompleted(int transID, int respCode) {
            }

            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            }
		};
		
		synchronized(inquiryCompletedEvent) {
            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, discListener);
            if (started) {
                System.out.println("wait for device inquiry to complete...");
                inquiryCompletedEvent.wait();
                System.out.println(devices.size() +  " device(s) found");
            }
        }
		return devices;
	}
}
