package com.mastercard.gpse.processing.managers;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

public class SSHTunnel {

    private static org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(SSHTunnel.class);

    private static Session session;
    String host = TestProperties.getInstance().getApiProperty("sshTunnel.host");
    String user = TestProperties.getInstance().getApiProperty("sshTunnel.user");
    String password = TestProperties.getInstance().getApiProperty("sshTunnel.password");
    int port = Integer.parseInt(TestProperties.getInstance().getApiProperty("sshTunnel.port"));
    int tunnelLocalPort = Integer.parseInt(TestProperties.getInstance().getApiProperty("sshTunnel.tunnelLocalPort"));
    String tunnelRemoteHost = TestProperties.getInstance().getApiProperty("sshTunnel.tunnelRemoteHost");
    int tunnelRemotePort = Integer.parseInt(TestProperties.getInstance().getApiProperty("sshTunnel.tunnelRemotePort"));

    public Session getSSHTunnelSession() {
        if (SSHTunnel.session == null) session = setUpSSHTunnel();
        return SSHTunnel.session;
    }

    public Session setUpSSHTunnel(){
        SSHTunnel.session = null;
        try {
            SSHTunnel.session = new JSch().getSession(user, host, port);
            SSHTunnel.session.setPassword(password);
            localUserInfo lui = new localUserInfo();
            SSHTunnel.session.setUserInfo(lui);
            SSHTunnel.session.connect();
            SSHTunnel.session.setPortForwardingL(tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);
            logger.info("SSH Tunnel connection established.");
            System.out.println("SSH Tunnel connection established.");
            SSHTunnel.session.sendKeepAliveMsg();
            return SSHTunnel.session;
        } catch (Exception e) {
            logger.info("Exception occured during SSH Tunnel connection established.");
            return null;
        }
    }

    class localUserInfo implements UserInfo {
        String passwd;
        public String getPassword(){ return passwd; }
        public boolean promptYesNo(String str){return true;}
        public String getPassphrase(){ return null; }
        public boolean promptPassphrase(String message){return true; }
        public boolean promptPassword(String message){return true;}
        public void showMessage(String message){}
    }

    public void sendKeepAliveMsg(){
        try {
            SSHTunnel.session.sendKeepAliveMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectSSHTunnel() {
        if (session != null)
            SSHTunnel.session.disconnect();
        System.out.println("SSH Tunnel connection disconnected.");
    }
}
