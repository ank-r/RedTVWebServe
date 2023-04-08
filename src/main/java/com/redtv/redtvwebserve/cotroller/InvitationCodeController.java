package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.service.InvitationCodeService;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName InvitationCodeController
 * @Description
 * @Author admin
 * @Time 2023/4/2 11:06
 * @Version 1.0
 */
@RestController
public class InvitationCodeController {


    private final InvitationCodeService invitationCodeService;

    @Autowired
    public InvitationCodeController(InvitationCodeService invitationCodeService) {
        this.invitationCodeService = invitationCodeService;
    }

    @GetMapping("/admin/invitation/list")
    public ResponseDetails list(@RequestParam Map<String, Object> params) {
        return ResponseDetails.ok().put("data", invitationCodeService.queryInvitation());
    }

    @PostMapping("/admin/invitation/create")
    public ResponseDetails create( ) {
        return ResponseDetails.ok().data(invitationCodeService.create());
    }
}
