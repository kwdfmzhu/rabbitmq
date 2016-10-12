package cn.zkw.common.rabbitmq.test;


import org.junit.Test;
import org.springframework.util.Base64Utils;
import java.io.*;

public class MqServiceImplTest {

    @Test
    public void xml() throws IOException, InterruptedException {
        String response = "/root/newstext/3c7071e6-7fde-11e6-8b71-37058987d9e5.txt\n/root/newstext/12cdf78b819257177a4a7ee48ed7de02.txt";
//        for(String str:response.split("\n")){
//            System.out.println(CommonUtils.getFileNoSuffixName(str));
//        }
//
//        String xx = "eyJjbGlwVXJsSGFzaCI6ICJkY2ExMjNkNjJlNDg2MTYyOWY0NzdjMzllY2U4NDUwMiIsICJwdWJsaXNoVGltZSI6ICIyMDE2LTA5LTA1IDEwOjQxOjM4IiwgImNsaXBEdXJhdGlvbiI6IDAsICJzb3VyY2VNZWRpYVBhdGgiOiAiIiwgInRyYWNrU291cmNlSWQiOiA5NSwgIm1ldGFVdWlkIjogIiIsICJjYXRlZ29yeSI6ICJ0ZXh0IiwgInNvdXJjZURuYVBhdGgiOiAiIiwgIm9mZmxpbmVUaW1lIjogIiIsICJjb250ZW50IjogIlx1NjIxMFx1NTI5Zlx1NTkwZFx1NGVjN1x1NTg1ZVx1NWMxNFx1NmJkNFx1ZmYwY1x1NGUwMVx1NGZjYVx1NjY1Nlx1NjIxMFx1NGUzYVx1NGU4Nlx1NGUwYVx1NmQ3N1x1NTkyN1x1NWUwOFx1OGQ1Ylx1NTM0MVx1NWU3NFx1NTM4Nlx1NTNmMlx1NGUwYVx1N2IyY1x1NGUwMFx1NGY0ZFx1NGUyNFx1NTkzYVx1NTFhMFx1NTE5Ylx1NTk1Nlx1Njc2Zlx1NzY4NFx1NzQwM1x1NTQ1OFx1MzAwMlx1NGY0Nlx1NjZmNFx1NTAzY1x1NWY5N1x1NGUzYVx1NWMwZlx1NGUwMVx1NWU4Nlx1Nzk1ZFx1NzY4NFx1NjYyZlx1ZmYwY1x1NGVkNlx1OTAxYVx1OGZjN1x1NjNmZFx1NGUwYlx1OGZkOVx1NGUwMFx1NjM5Mlx1NTQwZFx1OGQ1Ylx1NTFhMFx1NTE5Ylx1NTQwZVx1ZmYwY1x1N2VjOFx1NGU4ZVx1NjI1M1x1NzgzNFx1NGU4Nlx1ODFlYVx1NWRmMVx1OTU3Zlx1OGZiZTI5XHU0ZTJhXHU2NzA4XHU3Njg0XHU2MzkyXHU1NDBkXHU4ZDViXHU1MWEwXHU1MTliXHU4MzUyXHVmZjBjXHU4ZmQ5XHU0ZTVmXHU2NjJmXHU0ZWQ2XHU4MDRjXHU0ZTFhXHU3NTFmXHU2ZGFmXHU5MWNjXHU3YjJjXHU0ZThjXHU5NTdmXHU3Njg0XHU1MWEwXHU1MTliXHU4MzUyXHUzMDAyXHU4ZmQ5XHU0ZTVmXHU2NjJmXHU0ZTAxXHU0ZmNhXHU2NjU2XHU0ZTJhXHU0ZWJhXHU2MzkyXHU1NDBkXHU4ZDViXHU3Njg0XHU3YjJjXHU1MzQxXHU0ZThjXHU1MWEwXHVmZjBjXHU0ZWQ2XHU0ZTVmXHU4ZmZkXHU1ZTczXHU0ZTg2XHU3ZjU3XHU0ZjJmXHU5MDBhXHU2MzkyXHU1NDBkODBcdTU0MGVcdTc0MDNcdTYyNGJcdTYzOTJcdTU0MGRcdThkNWJcdTUxYTBcdTUxOWJcdTY1NzBcdTkxY2ZcdTc2ODRcdTY5OWNcdTk5OTZcdTMwMDJcdTczYjBcdTU3MjhcdWZmMGNcdTRlZDZcdTdlYzhcdTRlOGVcdTUzZWZcdTRlZTVcdTY3N2VcdTRlMDBcdTUzZTNcdTZjMTRcdTRlODYiLCAiY2xpcFJlc3VsdElkIjogMzAxMzAsICJjbGlwVXJsIjogImh0dHA6Ly9hcGkuc2luYS5jbi9zaW5hZ28vYXJ0aWNsZXYyLmpzb24/aWQ9Znh2cWN0czk0OTExNjctY29tb3Mtc3BvcnRzLWNtcyZ1aWQ9MGEwYmU2YjIyZjA5Yjc0YyZ3bT1iMjA3Jm9sZGNod209MTIwMzBfMDAwMSZpbWVpPTg2MDMxMTAyOTc0Nzk2MSZ1c2VyX3VpZD0yODE1NTI5NzExJmZyb209NjA0ODA5NTAxMiZwb3N0dD1uZXdzX25ld3NfdG91dGlhb19mZWVkXzEmY2h3bT0xMjAzMF8wMDAxIiwgInNvdXJjZVVybCI6ICJodHRwOi8vYXBpLnNpbmEuY24vc2luYWdvL3ZpZGVvX2xvY2F0aW9uLmpzb24/c2ZfaT00JnZpZGVvX2lkPTI1MDczNTc2NCZ2aWRlb19wbGF5X3VybD1odHRwJTNBJTJGJTJGYXNrLml2aWRlby5zaW5hLmNvbS5jbiUyRnZfcGxheV9pcGFkLnBocCUzRnZpZCUzRDE0MDcyODgyMCZmcm9tc2luYWdvPTEmZnJvbT02MDQ4MDk1MDEyIiwgInNvdXJjZVR5cGUiOiAiYXBwIiwgIm9mZnNldHMiOiBudWxsLCAic2FtcGxlS2V5IjogIiIsICJjbGlwU291cmNlIjogIlx1NjViMFx1NmQ2YVx1NGY1M1x1ODBiMlx1OGJhZiIsICJ2aWV3Q291bnQiOiAxNjY2OCwgImNsaXBUaXRsZSI6ICJcdTU2ZmRcdThkYjNcdTYyMThcdTRmMGFcdTY3MTdcdTUzNTVcdTU3M2FcdTU5NTZcdTkxZDFcdThmYmUxMzAwXHU0ZTA3IiwgIm1ldGFUaXRsZSI6ICIiLCAic291cmNlVXJsSGFzaCI6ICJmNzM5MDEwNmYxZGFjOTIxZDU3YzIzZDQ0YTU4ZjdmMCIsICJzb3VyY2VSZXN1bHRJZCI6IDE3MTU1MSwgInRodW1ibmFpbCI6ICIiLCAic25hcHNob3QiOiAiIn0=";
//        System.out.println(base64Decode(xx));

        response = "";
//            debug...
//            response = "/root/newstext/3c7071e6-7fde-11e6-8b71-37058987d9e5.txt\n/root/newstext/12cdf78b819257177a4a7ee48ed7de02.txt";
//            response = "/root/newstext/3c7071e6-7fde-11e6-8b71-37058987d9e5.txt";

//        Process process = Runtime.getRuntime().exec("/tmp/xx.py >/tmp/xx");
//        int exitValue = process.waitFor();
//        System.out.println(exitValue);
//        if (0 != exitValue) {
//            System.out.println("==============---------======");
//        }
//        InputStream in = process.getInputStream();
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        byte[] data = new byte[4096];
//        int count = -1;
//        while((count = in.read(data,0,4096)) != -1)
//            outStream.write(data, 0, count);
//
//        String xx =  new String(outStream.toByteArray());
//
//
//        System.out.println("==============" + xx);


        String xxx =  "123456";
        System.out.println(xxx.indexOf("123"));
        System.out.println(xxx.indexOf("12213"));


    }

    private String base64Decode(String content){
        return new String(Base64Utils.decodeFromString(content));
    }
}
