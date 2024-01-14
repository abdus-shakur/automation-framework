package com.xrame.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetYuppChannelName {
	
	public static void main(String[] args) {
		String url = "https://yupp.tamilultra.in/redbox.m3u8?id=2881";
		GetYuppChannelName cn = new GetYuppChannelName();
		cn.getChannelName(url);
//		cn.getChannelName();
	}
	
	public void getChannelName(String url) {
		HttpURLConnection cn;
		try {
			cn = (HttpURLConnection) URI.create(url).toURL().openConnection();
			cn.setConnectTimeout(10000);
			cn.setReadTimeout(10000);
			cn.connect();
			InputStream is = cn.getInputStream();
			int ch;
			String content = "";
			while((ch=is.read())!=-1) {
				content += (char)ch;
			}
			System.out.println("Url:"+url+";ChannelName:"+getChannelValue(content));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getChannelValue(String data) {
		Pattern pat = Pattern.compile("stream.ts.*21190/.*/(.*)/.*");
		Matcher mat = pat.matcher(data);
		mat.find();
//	    while(mat.find()) {
//			System.out.println("Matches");
//			for(int i = 0;i<=mat.groupCount();i++) {
//				System.out.println("Group:"+i+";Value:"+mat.group(i));
//			}
//	    }
		return mat.group(1);
	}
	
	public String getChannelName() {
		Pattern pat = Pattern.compile("stream.ts.*21190/.*/(.*)/.*");
		Matcher mat = pat.matcher(testContent);
		mat.find();
//	    while(mat.find()) {
			System.out.println("Matches");
			for(int i = 0;i<=mat.groupCount();i++) {
				System.out.println("Group:"+i+";Value:"+mat.group(i));
			}
//	    }
		return null;
	}
	
	String testContent = "#EXTM3U\r\n"
			+ "#EXT-X-VERSION:3\r\n"
			+ "#EXT-X-TARGETDURATION:5\r\n"
			+ "#EXT-X-MEDIA-SEQUENCE:12\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17060000_12.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17064000_13.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17068000_14.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17072000_15.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17076000_16.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17080000_17.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17084000_18.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17088000_19.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17092000_20.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17096000_21.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17100000_22.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17104000_23.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4.32,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17108000_24.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17112320_25.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17116320_26.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17120320_27.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17124320_28.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17128320_29.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17132320_30.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "#EXTINF:4,\r\n"
			+ "stream.ts?stream=http://0nelivezer0.rbtvnw.com:21190/vulcan/janamtv/l_161138048_17136320_31.ts&nimblesessionid=96277824&wmsAuthSign=c2VydmVyX3RpbWU9My8yNS8yMDIzIDk6NTc6MDEgQU0maGFzaF92YWx1ZT1iS24xTTdydnd6c1ZtSzZ6Ulh3Mk5BPT0mdmFsaWRtaW51dGVzPTI=\r\n"
			+ "\r\n"
			+ "";

}
