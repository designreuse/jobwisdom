package com.zefun.wechat.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.mapper.MemberComboProjectMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;

import net.sf.json.JSONArray;

/**
 * 会员疗程过期处理
* @author 高国藩
* @date 2016年8月20日 下午2:24:00
 */
public class MemberComboRecordJob {
    
    @Autowired private MemberComboProjectMapper memberComboProjectMapper;
    @Autowired private MemberComboRecordMapper memberComboRecordMapper;

    @Transactional
    public void execute() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String reviceDate = sdf.format(new Date());
        List<MemberComboRecord> memberComboRecords = memberComboRecordMapper.selectAll(reviceDate);
        List<Integer> recordIds = memberComboRecords.stream().map(m -> m.getRecordId()).collect(Collectors.toList());
        if (recordIds != null && recordIds.size() > 0){
            String str = JSONArray.fromObject(recordIds).toString();
            str = str.substring(1, str.length());
            str = str.substring(0, str.length()-1);
            Map<String, String> map = new HashMap<>();
            map.put("recordIds", str);
            memberComboProjectMapper.updateByRecordIds(map);
            memberComboRecordMapper.updateByDate(map);
        }
    }
    
}
