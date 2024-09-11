package xyz.itwill09.mapper;

import java.util.List;

import xyz.itwill09.dto.Userinfo;

public interface InerfaceMapper {
	int insertUserinfo(Userinfo userinfo);
	int updadteUserinfo(Userinfo userinfo);
	int deleteUserinfo(String userid);
	Userinfo selectUserinfo(String userid);
	List<Userinfo> ListselectUserinfoList();

}
