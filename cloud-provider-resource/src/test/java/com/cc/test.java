package com.cc;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.BaseTest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wj.springcloud.utils.SnowflakeIdWorker;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：actor
 * @date ：Created in 2021/8/10 8:13
 * @description：
 * @modified By：
 * @version: $
 */
public class test extends BaseTest {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    @Test
    public  void test2(){
        long l = snowflakeIdWorker.nextId();
        System.out.println(l);
    }

     @Test
    public void test1(){
        List<Car> cars= new ArrayList<>();
        cars.add(new Car("ssssss"));
           JSONArray jsonArray = convertSaveAndUpdateData(cars);
        // String post = HttpUtil.post("http://localhost:4001/resource/getResources", JSON.toJSONString(jsonArray));
         String post1 = HttpUtil.post("http://10.139.72.11:9002/jydwYjgk/monitor/videoCarInfo/addWhiteList1", JSON.toJSONString(jsonArray));
         System.out.println(post1);

     }
    private JSONArray convertSaveAndUpdateData(List<Car> cars) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        cars.stream().forEach(car -> {
            JSONObject json = new JSONObject();
            json.put("plateNo", "car.getCarNumber()");
            json.put("startTime", "");
            json.put("endTime", "");
            json.put("ownerName", "");
            json.put("ownerPhone", "");
            json.put("parkAuth", "");
            json.put("parkingType", "");
            json.put("createTime", "car.getCreateTime()");
            //  json.put("updateTime",car.getUpdateTime());
            json.put("plateColor", "");
            json.put("vehicleColor", "");
            json.put("vehicleType", "");
            json.put("sign", "");
            json.put("carModel", "car.getCarModel()");
            json.put("company", "car.getCompany()");
            //  json.put("allowExpireTime",car.getAllowExpireTime());
            jsonArray.add(json);
        });
        return jsonArray;
    }

    private JSONObject convertSaveAndUpdateData1(List<Car> cars) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        cars.stream().forEach(car -> {
            JSONObject json = new JSONObject();
            json.put("plateNo", "car.getCarNumber()");
            json.put("startTime", "");
            json.put("endTime", "");
            json.put("ownerName", "");
            json.put("ownerPhone", "");
            json.put("parkAuth", "");
            json.put("parkingType", "");
            json.put("createTime", "car.getCreateTime()");
            //  json.put("updateTime",car.getUpdateTime());
            json.put("plateColor", "");
            json.put("vehicleColor", "");
            json.put("vehicleType", "");
            json.put("sign", "");
            json.put("carModel", "car.getCarModel()");
            json.put("company", "car.getCompany()");
            //  json.put("allowExpireTime",car.getAllowExpireTime());
            jsonArray.add(json);
        });
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

    @Test
    public void test5() throws Exception {
        String filePath = "C:\\Users\\actor\\Pictures\\Camera Roll\\1.jpg";
//        MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
//        FileSystemResource resource = new FileSystemResource(new File(filePath));
//        map.add("file",resource);

        File picFile = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(picFile);
        MultipartFile multipartFile = new MockMultipartFile(picFile.getName(), picFile.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        Map map = new HashMap();
        map.put("file",multipartFile);
        map.put("aa","ccccc");
        String body = HttpUtil.createPost("http://10.139.115.50:4001/resource/upload")
                .contentType("multipart/form-data")
              //  .form("file",multipartFile)
                .form("aa","picFile")
                .execute()
                .body();
        System.out.println(body);
    }

    @Test
    public void test6() throws Exception {
        String filePath = "C:\\Users\\actor\\Pictures\\Camera Roll\\1.jpg";
        String sURL="http://10.139.115.50:4001/resource/upload";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(sURL);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        //builder.setCharset(Charset.forName("uft-8"));//设置请求的编码格式
//        builder.addTextBody("msg_id", "1029", ContentType.TEXT_PLAIN);
//        builder.addTextBody("lib_id", "1", ContentType.TEXT_PLAIN);
//        builder.addTextBody("person_name", "xiaoming", ContentType.TEXT_PLAIN);
        // 把文件加到HTTP的post请求中
        File f = new File(filePath);
        builder.addBinaryBody(
                "file",
                new FileInputStream(f),
                ContentType.APPLICATION_OCTET_STREAM,
                f.getName()
        );
        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(uploadFile);
        HttpEntity responseEntity = response.getEntity();
        String sResponse= EntityUtils.toString(responseEntity, "UTF-8");
        //打印请求返回的结果
        System.out.println("Post 返回结果"+sResponse);
    }


    @Test
    public void test7() throws Exception {
        File file = FileUtil.file("E:\\360MoveData\\Users\\actor\\Desktop\\1.xls");
        String type = FileTypeUtil.getType(file);
    }

    @Test
    public void test8() throws Exception {
        File file = new File("D:\\data\\jpg\\1.jpg");
        File newfile = new File( "D:\\data\\2.jpg");
        FileUtils.copyFile(file, newfile, false);
    }

}
