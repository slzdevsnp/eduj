package org.slzdev.clients.ftp;

import it.sauronsoftware.ftp4j.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Slf4j
public class SimpleFtpTestAcc {

    private FTPClient ftpClient;

    @Before
    public  void init() throws IOException, FTPIllegalReplyException, FTPException {
        if (this.ftpClient == null) {
            Properties properties = new Properties();
            properties.load(SimpleFtpTestAcc.class.getResourceAsStream("/application.properties"));

            String username = (String) properties.get("ftp.username.test");
            String pwdB64 = (String) properties.get ("ftp.pwd.test");
            String pwd = new String(Base64.getDecoder().decode(pwdB64));
            String wcloudIp="192.168.1.2";
            int port = 21;
            ftpClient = new FTPClient();
            ftpClient.connect(wcloudIp,port);
            ftpClient.login(username,pwd);
        }
    }

    @After
    public void tearDown() throws FTPException, IOException, FTPIllegalReplyException {
        if (ftpClient.isConnected()) {
            ftpClient.disconnect(true);
        }
    }

    @Test
    public void givenCurrentDirListIt() throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
        String cdir = ftpClient.currentDirectory();
        log.info("ftp cur dir - {}",cdir);
        assertThat(cdir,is("/"));
        FTPFile[] flist = ftpClient.list();
        log.info("current dir - {}  file listing - {}",cdir, flist);
    }

    @Test
    public void givenSpecifiedDirListIt() throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
        String sdir = "/Public/sz/tdata/case2_102720349_DEU_A/live";
        ftpClient.changeDirectory(sdir);
        String cdir = ftpClient.currentDirectory();
        assertThat(cdir, is(sdir));

        FTPFile[] flist = ftpClient.list();
        log.info("current dir - {}  file listing - {}",cdir, flist);
        assertThat(flist.length, is(3));
    }


    @Test
    public void givenPathGetChangeDate() throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
        String sdir = "/Public/sz/tdata/case2_102720349_DEU_A/live";

        ftpClient.changeDirectory(sdir);
        log.info("files - {}",ftpClient.list());
        String filename= "5002060_Pwr_EEX_PRO_Solar_DEU_A_2021-02-15.CSV";
        java.util.Date md = ftpClient.modifiedDate(filename);
        ZonedDateTime zdt =  md.toInstant().atZone(ZoneId.of("Z"));
        log.info("mod date - {} ; in utc - {} for file - {}",md, zdt, filename);
    }

    @Test
    public void givenFileDownloadFile() throws FTPException, IOException, FTPIllegalReplyException, FTPDataTransferException, FTPAbortedException {
        ftpClient.setType(FTPClient.TYPE_BINARY);  //ok between unix systems
        String sdir = "/Public/sz/tdata/case2_102720349_DEU_A/live";
        ftpClient.changeDirectory(sdir);
        String filename= "5002060_Pwr_EEX_PRO_Solar_DEU_A_2021-02-15.CSV";
        String localFile = "/tmp/" + filename;
        ftpClient.download(filename, new java.io.File(localFile)); //thread blocking call  1 simultaneous transfer per ftp connection
        assertThat( new File(localFile).exists(), is(true));
    }

    @Test
    public void givenLargeFileDownloadItWithCompression() throws FTPException, IOException, FTPIllegalReplyException, FTPDataTransferException, FTPAbortedException {
        ftpClient.setType(FTPClient.TYPE_BINARY);  //ok between unix systems
        String sdir = "/Public/sz/tdata/case1_101684603/historic";
        ftpClient.changeDirectory(sdir);
        String filename= "5010886_HIST_Pwr_PCA_CON_ECEns_PERC_BEL_F_2021.CSV";
        String localFile = "/tmp/" + filename;

        boolean compressionSupported = ftpClient.isCompressionSupported();
        if (compressionSupported) {
            ftpClient.setCompressionEnabled(compressionSupported);
        }
        LocalDateTime sdt = LocalDateTime.now();
        ftpClient.download(filename, new java.io.File(localFile)); //thread blocking call  1 simultaneous transfer per ftp connection
        LocalDateTime edt = LocalDateTime.now();
        log.info("tranfer with compression - {}  of file size - {} KB took - {} sec ",compressionSupported,
                new File(localFile).length() /1000L,
                Duration.between(sdt,edt).toSeconds());
        assertThat( new File(localFile).exists(), is(true));
    }



    @Ignore
    @Test
    public void givenBase64Decode() {
        String originalS="blah";
        String encodedS=Base64.getEncoder().encodeToString(originalS.getBytes());
        byte[] decodedBytes = Base64.getDecoder().decode(encodedS);
        String decodedS = new String(decodedBytes);
        log.info("encoded: - {} decoded - {}",encodedS, decodedS);
        assertThat(decodedS,is(originalS));
    }
}
