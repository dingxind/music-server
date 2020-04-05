package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Collect;
import com.example.demo.service.impl.CollectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Api(tags = "用户收藏接口")
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

    //    添加收藏的歌曲
    @PostMapping(value = "/api/addCollections")
    @ApiOperation(" 添加收藏的歌曲")
    public Object addCollections(HttpServletRequest req) {

        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String song_id = req.getParameter("songId");
        String song_list_id = req.getParameter("songListId");

        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        if (new Byte(type) == 0) {
            collect.setSongId(Integer.parseInt(song_id));
        } else if (new Byte(type) == 1) {
            collect.setSongListId(Integer.parseInt(song_list_id));
        }
        collect.setCreateTime(new Date());
        boolean res = collectService.addCollection(collect);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "收藏成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏失败");
            return jsonObject;
        }
    }

    @PostMapping(value = "/api/collectionList")
    public Object collectionList(HttpServletRequest req) {

        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String song_id = req.getParameter("songId");
        String song_list_id = req.getParameter("songListId");

        if (song_id == "") {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏失败");
            return jsonObject;
        } else if (collectService.existSongId(Integer.parseInt(song_id))) {
            jsonObject.put("code", 2);
            jsonObject.put("msg", "已收藏");
            return jsonObject;
        }
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        if (new Byte(type) == 0) {
            collect.setSongId(Integer.parseInt(song_id));
        } else if (new Byte(type) == 1) {
            collect.setSongListId(Integer.parseInt(song_list_id));
        }
        collect.setCreateTime(new Date());
        boolean res = collectService.addCollection(collect);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "收藏成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏失败");
            return jsonObject;
        }
    }

    //    删除收藏的歌曲
    @ApiOperation("删除收藏的歌曲")
    @GetMapping(value = "/api/deleteCollects")
    public Object deleteCollects(HttpServletRequest req) {
        String id = req.getParameter("id");
        return collectService.deleteCollect(Integer.parseInt(id));
    }

    //    更新收藏
    @PostMapping(value = "/api/updateCollectMsgs")
    @ApiOperation("更新收藏")
    public Object updateCollectMsgs(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String type = req.getParameter("type").trim();
        String song_id = req.getParameter("songId").trim();
//        String song_list_id = req.getParameter("songListId").trim();

        Collect collect = new Collect();
        collect.setId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(song_id));

        boolean res = collectService.updateCollectMsg(collect);
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

    //    返回所有用户收藏列表
    @ApiOperation("返回所有用户收藏列表")
    @GetMapping(value = "/allCollects")
    public Object allCollects() {
        return collectService.allCollect();
    }

    //    返回的制定用户ID收藏列表
    @ApiOperation("返回的制定用户ID收藏列表")
    @GetMapping(value = "/myCollection")
    public Object myCollection(HttpServletRequest req) {
        String userId = req.getParameter("userId");
        return collectService.myCollectOfSongs(Integer.parseInt(userId));
    }
}
