/*
 *   Copyright 1999-2016 Asiainfo Technologies(China),Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package main.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import main.controller.SearchController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * @version v 1.0 on 2016/5/9 14:18
 * @auther william.xu
 */
public class ShellUtil {

	private final static int DEFAULT_SSH_PORT = 22;
	private final static int CONNECT_TIMEOUT = 30 * 1000;
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
	
	public static void sftp(String address, String user, String password, InputStream src, String destFile)
			throws Exception {

		JSch jsch = new JSch();
		Session session = jsch.getSession(user, address);
		session.setPassword(password);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		// channelSftp.setFilenameEncoding("gbk");//中文乱码需修改ChannelSFTP.SENDINIT方法，把buf.putInt(3);修改为buf.putInt(2);
		try {
			channelSftp.put(src, destFile);
		} finally {
			if (channelSftp != null && channelSftp.isConnected()) {
				channelSftp.disconnect();
			}
			if (session != null && session.isConnected()) {
				session.disconnect();
			}
		}

	}

	public static String ssh(String address, String user, String password, String script) throws Exception {

		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;
		try {
			session = jsch.getSession(user, address, DEFAULT_SSH_PORT);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(CONNECT_TIMEOUT);
			channel = session.openChannel("shell");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(channel.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(channel.getInputStream(), "GBK"));
			BufferedReader errReader = new BufferedReader(new InputStreamReader(channel.getExtInputStream(), "GBK"));
			channel.connect(CONNECT_TIMEOUT);

			writer.write(script);
			writer.newLine();
			writer.write("exit");
			writer.newLine();
			writer.flush();
			String result = "";
			String msg = "";
			while ((msg = errReader.readLine()) != null) {
				LOGGER.error(msg);
				result += msg;
			}
			while ((msg = reader.readLine()) != null) {
				LOGGER.info(msg);
				result += msg;
			}
			int status = channel.getExitStatus();
			if (status != 0) {
				String errorMsg = "调用远程服务器脚本返回错误状态=" + status;
				throw new Exception(errorMsg);
			}
			return result;
		} catch (Exception e) {
			LOGGER.error("调用远程服务器脚本出现错误:address=" + address + ",user=" + user + ",script=" + script, e);
			throw e;
		} finally {
			if (channel != null && channel.isConnected()) {
				channel.disconnect();
			}
			if (session != null && session.isConnected()) {
				session.disconnect();
			}
		}

	}

}
