package pku.ss.webService;

import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

public class WSHelper {
	final static String WSUrl = "http://10.0.2.2:39065/ToBuyService.asmx";

	private static String namespace = "http://tempuri.org/";
	//private static String namespace = "http://10.0.2.2:39065/ToBuyService.asmx";

	/*************************************
	 * 获取web services内容
	 * 
	 * @param url
	 * @param params
	 * @return
	 *************************************/
	public static String GetResponse(String method,
			List<BasicNameValuePair> params) {

		try {
			String url = WSUrl;
			SoapObject request = new SoapObject(namespace, method);
			for (int i = 0, len = params.size(); i < len; i++) {
				request.addProperty(params.get(i).getName(), params.get(i)
						.getValue());
			}
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			// 设置30秒超时
			MyAndroidHttpTransport transport = new MyAndroidHttpTransport(url,
					30000);

			transport.call(namespace + method, envelope);

			SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
			return result.toString();
		} catch (Exception e) {
			return "Error:calling the web services error";
		}
	}
}