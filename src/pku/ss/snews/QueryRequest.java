package pku.ss.snews;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import pku.ss.webService.*;

public class QueryRequest extends Fragment {
	EditText edt = null;
	Button btn = null;
	TextView tex = null;
	static QueryRequest newInstance() {
		QueryRequest newFragment = new QueryRequest();
        return newFragment;
    }
	
	
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	View view;
    	view = inflater.inflate(R.layout.query_request, container, false);
    	edt = (EditText)view.findViewById(R.id.edtiNum);
        btn = (Button)view.findViewById(R.id.queryBtn);
        tex = (TextView)view.findViewById(R.id.textview);
		btn.setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
					String phone = edt.getText().toString().trim();
					if("".equals(phone)){
						tex.setText("");
						edt.setText("");
						edt.setError("请输入内容，同学！");
						edt.requestFocus();
					}
					 getInfo2(phone);  
		
					}
				});
		return view;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  
    }
    
    public void getInfo2(String phone){
    	List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();  
    	params.add(new BasicNameValuePair("id", phone));
    	String result = WSHelper.GetResponse("QueryResult", params);
    	tex.setText(result);
    }
    
    /*public void getRemoteInfo(String phone){
    	 
    	//命名空间
    	final String NAMESPACE = "http://WebXml.com.cn/";
    	//调用方法名
    	final String METHOD_NAME = "getMobileCodeInfo";
    	//URL
    	final String NETADDRESS = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
    	//saop_action 
    	String soapAction = NAMESPACE+METHOD_NAME;
    	
    }
    public void getInfo(String num){
    	String target = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
    	URL url;
    	String result = "";
    	try{
    		url = new URL(target);
    		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
    		urlConn.setRequestMethod("POST");
    		urlConn.setDoInput(true);
    		urlConn.setDoOutput(true);
    		urlConn.setUseCaches(false);
    		urlConn.setInstanceFollowRedirects(true);
    		urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    		DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());
    		String param = "mobileCode="+URLEncoder.encode(num, "utf-8")+"&userID=";
    		out.writeBytes(param);
    		out.flush();
    		out.close();
    		if(urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
    			InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
    			BufferedReader buffer = new BufferedReader(in);
    			String inputLine = null;
    			while((inputLine = buffer.readLine()) !=null){
    				result +=inputLine+"\n" ;
    			}
    			tex.setText(result);
    			in.close();
    			}
    		urlConn.disconnect();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }*/

}
