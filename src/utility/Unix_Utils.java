package utility;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.*;
import java.io.DataInputStream;  
import java.io.DataOutputStream; 
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
 


public class Unix_Utils {
		
	public static Session connect(String host_name, String username, String password) {
	
		java.util.Properties config = new java.util.Properties(); 
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
        Session session = null;
		try {
			session = jsch.getSession(username, host_name, 22);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        session.setPassword(password);
        session.setConfig(config);
        try {
			session.connect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Connected");
	   return session;		
	}
	
	
	public static String execute_Command(String arg, Session session) throws JSchException, IOException{
		String output = null;
		Channel channel=session.openChannel("exec");
        ((ChannelExec) channel).setCommand(arg);
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);
         
        InputStream in=channel.getInputStream();
        channel.connect();
        byte[] tmp=new byte[1024];
        while(true){
          while(in.available()>0){
            int i=in.read(tmp, 0, 1024);
            if(i<0)break;
            output = new String(tmp, 0, i);
            return output;
          }
        }
	}

	
	public static void connection_close(Session session) { 
		try {
			session.disconnect();
		    } 
		catch (Exception e) {
			  e.printStackTrace();
		    }
	}
	
}
	

