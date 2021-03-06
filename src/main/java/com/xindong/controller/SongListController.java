package com.xindong.controller;

import com.alibaba.fastjson.JSONObject;
import com.xindong.entities.SongList;
import com.xindong.service.impl.SongListServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@Api(tags = "歌单接口")
public class SongListController {

    @Autowired
    private SongListServiceImpl songListService;

    //    添加歌单
    @ApiOperation("添加歌单")
    @PostMapping(value = "/api/addSongList")
    public Object addSongList(@RequestBody SongList songList) {
        JSONObject jsonObject = new JSONObject();
//        String title = req.getParameter("title").trim();
//        String pic = req.getParameter("pic").trim();
//        String introduction = req.getParameter("introduction").trim();
//        String style = req.getParameter("style").trim();
//
//        SongList songList = new SongList();
//        songList.setTitle(title);
//        songList.setPic(pic);
//        songList.setIntroduction(introduction);
//        songList.setStyle(style);

        boolean res = songListService.ifAdd(songList);
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

    //    删除歌单
    @ApiOperation("删除歌单")
    @GetMapping(value = "/api/deleteSongLists/{id}")
    public Object deleteSongLists(@PathVariable String id) {
//        String id = req.getParameter("id");
        return songListService.deleteSongList(Integer.parseInt(id));
    }

    //    更新歌单信息
    @ApiOperation("更新歌单信息")
    @PostMapping(value = "/api/updateSongListMsgs")
    public Object updateSongListMsgs(@RequestBody SongList songList) {
        JSONObject jsonObject = new JSONObject();
//        String id = req.getParameter("id").trim();
//        String title = req.getParameter("title").trim();
//        String pic = req.getParameter("pic").trim();
//        String introduction = req.getParameter("introduction").trim();
//        String style = req.getParameter("style").trim();
//
//        SongList songList = new SongList();
//        songList.setId(Integer.parseInt(id));
//        songList.setTitle(title);
//        songList.setPic(pic);
//        songList.setIntroduction(introduction);
//        songList.setStyle(style);

        boolean res = songListService.updateSongListMsg(songList);
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

    //    更新歌单图片
    @ApiOperation("更新歌单图片")
    @PostMapping(value = "/api/updateSongListImg")
    public Object updateSongListImg(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean res = songListService.updateSongListImg(songList);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeAvatorPath);
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



    //    返回指定标题对应的歌单
    @ApiOperation("返回指定标题对应的歌单")
    @GetMapping(value = "/api/songAlbum/{title}")
    public Object songAlbum(@PathVariable  String title) {
//        String title = req.getParameter("title").trim();
        return songListService.songAlbum(title.trim());
    }

    //    返回标题包含文字的歌单
    @ApiOperation("返回标题包含文字的歌单")
    @GetMapping(value = "/api/songList/likeTitle/{title}")
    public Object likeTitle(@PathVariable  String title) {
//        String title = req.getParameter("title").trim();
        return songListService.likeTitle('%' + title.trim() + '%');
    }

    //    返回指定类型的歌单
    @ApiOperation("返回指定类型的歌单")
    @GetMapping(value = "/api/songList/likeStyle/{style}")
    public Object likeStyle(@PathVariable  String style) {
//        String style = req.getParameter("style").trim();
        return songListService.likeStyle(style.trim());
    }

    //    返回所有歌单
    @ApiOperation("返回所有歌单")
    @GetMapping(value = "/listSongLists")
    public Object toSongList() {
        return songListService.listSongLists();
    }

}















