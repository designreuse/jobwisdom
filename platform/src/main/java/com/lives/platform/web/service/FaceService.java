package com.lives.platform.web.service;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.common.exception.ServiceException;
import com.lives.platform.common.util.FaceUtil;
import com.lives.platform.web.dto.BaseDto;
import com.lives.platform.web.entity.FaceInfo;
import com.lives.platform.web.entity.FacesetStoreKey;
import com.lives.platform.web.mapper.FaceInfoMapper;
import com.lives.platform.web.mapper.FaceSearchRecordMapper;
import com.lives.platform.web.mapper.FacesetStoreMapper;

/**
 * 人脸识别
 * @author 张进军
 * @date Jun 30, 2015 2:50:01 PM 
 */
@Service
public class FaceService {
	@Autowired
	private FaceInfoMapper faceInfoMapper;
	
	@Autowired
	private FacesetStoreMapper facesetStoreMapper;
	
	@Autowired
	private FaceSearchRecordMapper faceSearchRecordMapper;
	
	/** 每个用户在faceset中的最大数量值 */
	private final int USER_FASE_IN_SET_COUNT = 10;
	/** 每个faceset存储的face数量值 */
	private final int FASE_SET_COUNT = 10000;
	
	public BaseDto addFace(Integer storeId, Integer userId,String faceId) throws ServiceException{
		//当前用户face数量在faceset中达到上限
		if(isMaxForUser(userId)){
			return new BaseDto(-1, "个人人脸数量已上限");
		}
		
		//如果查询不到店铺对应的faceset
		String setId = facesetStoreMapper.selectSetIdByStoreId(storeId);
		if(StringUtils.isBlank(setId)){
			return new BaseDto(-1, "当前店铺未获得脸部识别功能");
		}
		
		//当前faceset下face数量已达到上限，创建新的faceset
		if(isMaxForSet(setId)){
			JSONObject json = FaceUtil.createFaceset(faceId);
			setId = json.getString("faceset_id");
			FacesetStoreKey faceset = new FacesetStoreKey();
			faceset.setSetId(setId);
			faceset.setStoreId(storeId);
			facesetStoreMapper.insert(faceset);
		}
		
		//将face添加到faceset
		FaceUtil.addFaceByFacesetId(setId, faceId);
		
		//保存数据
		FaceInfo faceInfo = new FaceInfo();
		faceInfo.setFaceId(faceId);
		faceInfo.setSetId(setId);
		faceInfo.setUserId(userId);
		faceInfoMapper.insert(faceInfo);
		
		//刷新faceset，应换成异步方式
		FaceUtil.trainFacesetById(setId);
		
		return new BaseDto(0, "ok");
	}
	
	public BaseDto search(Integer storeId, String faceId) throws ServiceException{
		String facesetId = facesetStoreMapper.selectSetIdByStoreId(storeId);
		JSONObject json = FaceUtil.searchByFacesetId(faceId, facesetId, false);
		JSONObject matchObj = json.getJSONArray("candidate").getJSONObject(0);
		String matchFaceId = matchObj.getString("face_id");
		FaceInfo faceInfo = faceInfoMapper.selectByPrimaryKey(matchFaceId);
		matchObj.put("userId", faceInfo.getUserId());
		return new BaseDto(0, matchObj); 
	}
	
	public boolean isMaxForUser(Integer userId){
		int count = faceInfoMapper.selectCountByUserId(userId);
		if(count < USER_FASE_IN_SET_COUNT){
			return false;
		}
		return true;
	}
	
	public boolean isMaxForSet(String setId){
		int count = faceInfoMapper.selectCountBySetId(setId);
		if(count < FASE_SET_COUNT){
			return false;
		}
		return true;
	}
}
