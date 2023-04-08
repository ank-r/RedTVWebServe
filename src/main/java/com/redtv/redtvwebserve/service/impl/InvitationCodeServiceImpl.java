package com.redtv.redtvwebserve.service.impl;

import com.redtv.redtvwebserve.dao.InvitationCodeDao;
import com.redtv.redtvwebserve.entity.InvitationCodeEntity;
import com.redtv.redtvwebserve.service.InvitationCodeService;
import com.redtv.redtvwebserve.utils.HostHolder;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName InvitationCodeServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/4/2 11:08
 * @Version 1.0
 */
@Service
public class InvitationCodeServiceImpl implements InvitationCodeService {


    private final InvitationCodeDao invitationCodeDao;

    public InvitationCodeServiceImpl(InvitationCodeDao invitationCodeDao) {
        this.invitationCodeDao = invitationCodeDao;
    }

    @Override
    public Object queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public InvitationCodeEntity create( ) {
        UserInfo userInfo = HostHolder.getUser();
        InvitationCodeEntity invitationCodeEntity = new InvitationCodeEntity();
        invitationCodeEntity.setCreateTime(System.currentTimeMillis());
        invitationCodeEntity.setCreateUser(userInfo.getId());
        invitationCodeEntity.setUseStatus(1);
        invitationCodeEntity.setCode(UUID.randomUUID().toString().replace(" ", ""));
        invitationCodeDao.insert(invitationCodeEntity);

        return invitationCodeEntity;
    }

    @Override
    public List<InvitationCodeEntity> queryInvitation() {


        return invitationCodeDao.selectByMap(null);
    }
}
