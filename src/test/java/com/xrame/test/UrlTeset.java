package com.xrame.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class UrlTeset {

	UrlTeset(int i) {
		this.i = i;
	}

	int i;
	
	static {
		
	}

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
		executor.setMaximumPoolSize(5000);
		for (int i = 1970; i >= 1000; i--) {
			AtomicInteger integer = new AtomicInteger(i);
			executor.execute(() -> new UrlTeset(integer.get()).testUrl());
		}
		executor.shutdown();
	}
	
	public synchronized static void writeDataToFile(String data) {
		File file = new File(System.getProperty("user.dir")+"/src/test/resources/yupp_channel_data.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		try (FileWriter fileWriter = new FileWriter(file,true)){
			fileWriter.write(data+System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testUrl() {
		try {
			HttpURLConnection connection = (HttpURLConnection) URI.create("https://yupp.tamilultra.in/redbox.m3u8?id=" + i).toURL().openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			connection.connect();
			InputStream os = connection.getInputStream();
			int ch;
			String test = "";
			while ((ch = os.read()) != -1) {
				test += (char) ch;
			}
			System.out.print("Success:" + i + ";contentLength:" + test.length()+";");
			try {
				String channelName = new GetYuppChannelName().getChannelValue(test);
				System.out.println("Channel:"+channelName+";");
				writeDataToFile("https://yupp.tamilultra.in/redbox.m3u8?id=" + i+","+channelName);
			}catch(Exception y) {
				System.out.println("Channel:Exp");
				writeDataToFile("https://yupp.tamilultra.in/redbox.m3u8?id=" + i+",");
			}
		} catch (Exception e) {
			System.out.println("Failed:" + i + "; Exception:" + e.getMessage());
		}
	}

}
