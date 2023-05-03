package com.redtv.redtvwebserve.service;

import com.redtv.redtvwebserve.entity.InvitationCodeEntity;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InvitationService
 * @Description
 * @Author admin
 * @Time 2023/4/2 11:07
 * @Version 1.0
 */

public interface InvitationCodeService {
    Object queryPage(Map<String, Object> params);

    InvitationCodeEntity create();

    List<InvitationCodeEntity> queryInvitation();

    InvitationCodeEntity getInvitationCode(String code);

    int useInvitationCode(InvitationCodeEntity invitationCodeEntity);
}
