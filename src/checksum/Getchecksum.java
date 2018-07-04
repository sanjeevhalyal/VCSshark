package checksum;

import versiontree.versiontree;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Getchecksum {

    public static String getchecksum(String datafile) throws IOException, NoSuchAlgorithmException {
        FileInputStream fis = new FileInputStream(datafile);

        return getsha(fis);
    }

    public static String getdirectorychecksum(List<versiontree> directory) throws IOException, NoSuchAlgorithmException {

        String str="";
        for(versiontree file : directory)
        {
            str=str+file.getShavalue();
        }

        // convert String into InputStream
        InputStream is = new ByteArrayInputStream(str.getBytes());

        return getsha(is);


    }

    private static String getsha(InputStream is)throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA1");

        byte[] dataBytes = new byte[1024];

        int nread = 0;

        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }
        ;

        byte[] mdbytes = md.digest();

        //convert the byte to hex format
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }
}
