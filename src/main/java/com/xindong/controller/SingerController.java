package com.xindong.controller;

import com.alibaba.fastjson.JSONObject;
import com.xindong.entities.Singer;
import com.xindong.service.impl.SingerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Api(tags = "歌手接口")
public class SingerController {

    @Autowired
    private SingerServiceImpl singerService;

    //    添加歌手
    @ApiOperation("添加歌手")
    @PostMapping(value = "/api/addSinger")
    public Object addSinger(@RequestBody Singer singer) {
        JSONObject jsonObject = new JSONObject();
//        String name = req.getParameter("name").trim();
//        String sex = req.getParameter("sex").trim();
//        String pic = req.getParameter("pic").trim();
//        String birth = req.getParameter("birth").trim();
//        String location = req.getParameter("location").trim();
//        String introduction = req.getParameter("introduction").trim();
//
//        Singer singer = new Singer();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date myBirth = new Date();
//        try {
//            myBirth = dateFormat.parse(birth);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        singer.setName(name);
//        singer.setSex(new Byte(sex));
//        singer.setPic(pic);
//        singer.setBirth(myBirth);
//        singer.setLocation(location);
//        singer.setIntroduction(introduction);

        boolean res = singerService.ifAdd(singer);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "添加成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "添加失败");
            return jsonObject;
        }
    }

    //    删除歌手
    @ApiOperation("删除歌手")
    @GetMapping(value = "/api/deleteSingers/{id}")
    public Object deleteSingers(@PathVariable String id ) {
//        String id = req.getParameter("id");
        return singerService.deleteSinger(Integer.parseInt(id));
    }

    //    更新歌手信息
    @ApiOperation("更新歌手信息")
    @PostMapping(value = "/api/updateSingerMsgs")
    public Object updateSingerMsgs(@RequestBody Singer singer ) {
        JSONObject jsonObject = new JSONObject();
//        String id = req.getParameter("id").trim();
//        String name = req.getParameter("name").trim();
//        String sex = req.getParameter("sex").trim();
//        String pic = req.getParameter("pic").trim();
//        String birth = req.getParameter("birth").trim();
//        String location = req.getParameter("location").trim();
//        String introduction = req.getParameter("introduction").trim();
//
//        Singer singer = new Singer();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date myBirth = new Date();
//        try {
//            myBirth = dateFormat.parse(birth);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        singer.setId(Integer.parseInt(id));
//        singer.setName(name);
//        singer.setSex(new Byte(sex));
//        singer.setPic(pic);
//        singer.setBirth(myBirth);
//        singer.setLocation(location);
//        singer.setIntroduction(introduction);

        boolean res = singerService.updateSingerMsg(singer);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }

    @ApiOperation("文件上传")
    @PostMapping(value = "/api/updateSingerImg")
    public Object updateSingerImg(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean res = singerService.updateSingerImg(singer);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("pic", storeAvatorPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            } else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败" + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;
        }
    }

    //    返回所有歌手
    @ApiOperation("返回所有歌手")
    @GetMapping(value = "/listSingers")
    public Object toSingerList() {
        return singerService.listSingers();
    }

    //    根据歌手名查找歌手
    @ApiOperation("根据歌手名查找歌手")
    @GetMapping(value = "/searachSingers/{name}")
    public Object searachSingers(@PathVariable  String name) {
//        String name = req.getParameter("name").trim();
        return singerService.searachSinger(name.trim());
    }

    //    根据歌手性别查找歌手
    @ApiOperation("根据歌手性别查找歌手")
    @GetMapping(value = "/api/singer/{sex}")
    public Object SingerSex(@PathVariable String sex) {
//        String sex = req.getParameter("sex").trim();
        return singerService.singerSex(Integer.parseInt(sex.trim()));
    }
}
