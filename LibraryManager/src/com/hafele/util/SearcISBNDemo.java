package com.hafele.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.json.JSONObject;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version ����ʱ�䣺2017��10��13�� ����3:47:04
* ͼ��ISBN��ѯ���ô���
* �ù�������ʹ��������API�ֱ�Ϊ �ۺ�����API�ͼ�������API��������APIʵ��ͬ���Ĺ��ܡ���ͨ��ͼ���Ż�ȡͼ����Ϣ����Ѵ�������
*/
public class SearcISBNDemo {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //�����������KEY �� �ۺ�����API
    public static final String APPKEY_JUHE ="acfdff0a5cb840678cc0d2553402913f";//��API��Ѵ��������������е�https://www.juhe.cn/����appkey
 
    //�����������KEY �� ��������API
    public static final String APPKEY_JISU ="86499d759f8a2f82";//��API��Ѵ��������������е�https://www.jisuapi.com����appkey
    
    //ͼ��ISBN���� �� �ۺ�����API
    public static String getRequestJuhe(String ISBN){
        String result =null;
        String url ="http://feedback.api.juhe.cn/ISBN";//����ӿڵ�ַ
        Map<String, Object> params = new HashMap<String, Object>();//�������
        params.put("key",APPKEY_JUHE);//Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)
        params.put("sub",ISBN);//ͼ��ISBN����
 
        try {
            result = netJuhe(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
                JOptionPane.showMessageDialog(null, "�޷���ѯ����ͼ����Ϣ��");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }
    
  //ͼ��ISBN���� �� ��������API
    public static String getRequestJisu(String ISBN){
        String result =null;
        String url ="http://api.jisuapi.com/isbn/query";//����ӿڵ�ַ
        Map<String, Object> params = new HashMap<String, Object>();//�������
        params.put("isbn",ISBN);//ͼ��ISBN����
        params.put("appkey",APPKEY_JISU);//Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)           
 
        try {
            result = netJisu(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("status")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("status")+":"+object.get("msg"));
                JOptionPane.showMessageDialog(null, "�޷���ѯ����ͼ����Ϣ��");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }
    
    /**
     *
     * @param strUrl �����ַ
     * @param params �������
     * @param method ���󷽷�
     * @return  ���������ַ���
     * @throws Exception
     * �ۺ�����API
     */
    public static String netJuhe(String strUrl, Map<String, Object> params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            System.out.println(url);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);//ָ�������ַ
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
    
    /**
    *
    * @param strUrl �����ַ
    * @param params �������
    * @param method ���󷽷�
    * @return  ���������ַ���
    * @throws Exception
    * �ۺ�����API
    */
   public static String netJisu(String strUrl, Map<String, Object> params,String method) throws Exception {
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method==null || method.equals("GET")){
               strUrl = strUrl+"?"+urlencode(params);
           }
           URL url = new URL(strUrl);
           System.out.println(url);
           conn = (HttpURLConnection) url.openConnection();
           if(method==null || method.equals("GET")){
               conn.setRequestMethod("GET");
           }else{
               conn.setRequestMethod("POST");
               conn.setDoOutput(true);
           }
           conn.setUseCaches(false);
           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
           conn.setReadTimeout(DEF_READ_TIMEOUT);
           conn.setInstanceFollowRedirects(false);
           conn.connect();
           if (params!= null && method.equals("POST")) {
               try {
                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                       out.writeBytes(urlencode(params));
               } catch (Exception e) {
                   // TODO: handle exception
               }
           }
           InputStream is = conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               reader.close();
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }
 
    //��map��תΪ���������
    @SuppressWarnings("rawtypes")
	public static String urlencode(Map<String,Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
