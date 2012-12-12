package pku.ss.webService;

import java.io.IOException;

import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;

public class MyAndroidHttpTransport extends HttpTransportSE {

	private int timeout = 30000; // Ĭ�ϳ�ʱʱ��Ϊ30s

	public MyAndroidHttpTransport(String url) {
		super(url);
	}

	public MyAndroidHttpTransport(String url, int timeout) {
		super(url);
		this.timeout = timeout;
	}

	// ���õ��ó�ʱ����,Ĭ��
	protected ServiceConnection getServiceConnection(String url)
			throws IOException {
		ServiceConnectionSE serviceConnection = new ServiceConnectionSE(url);
		serviceConnection.setConnectionTimeOut(timeout);
		return new ServiceConnectionSE(url);
	}
}